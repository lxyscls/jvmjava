/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.ClassFile;
import com.github.lxyscls.jvmjava.classfile.ClassReader;
import com.github.lxyscls.jvmjava.classpath.ClassPath;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sk-xinyilong
 */
public class ClassLoader {
    private final ClassPath cp;
    private final Map<String, Jclass> classMap;
    
    public ClassLoader(ClassPath cp) throws IOException {
        this.cp = cp;
        classMap = new HashMap<>();
        
        loadBasicClasses();
        loadPrimitiveClasses();
    }
    
    public final Jclass loadClass(String name) throws IOException {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        }
        
        Jclass cls;
        if (name.startsWith("[")) {
            cls = loadArrayClass(name);
        } else {
            cls = loadNonArrayClass(name);
        }
        
        Jclass jlClassClass = classMap.get("java/lang/Class");
        if (jlClassClass != null) {
            cls.setClassObject(jlClassClass.newObject());
            cls.getClassObject().setExtra(cls);
        }
        return cls;
    }

    private Jclass loadArrayClass(String name) throws IOException {
        Jclass cls = defineClass(name);
        
        classMap.put(cls.getClassName(), cls);
        System.out.printf("[Loaded %s]\n", cls.getClassName());
        return cls;
    }    
    
    private Jclass loadNonArrayClass(String name) throws IOException {
        Jclass cls = defineClass(this.cp.readClass(name));
        link(cls);
        classMap.put(cls.getClassName(), cls);
        System.out.printf("[Loaded %s]\n", cls.getClassName());
        return cls;
    }
    
    private Jclass defineClass(String name) throws IOException {
        Jclass cls = new Jclass(name, true);
        cls.setClassLoader(this);
        resolveSuperClass(cls);
        resolveInterfaces(cls);
        return cls;
    }
    
    private Jclass defineClass(byte[] data) throws IOException {
        Jclass cls = new Jclass(new ClassFile(new ClassReader(data)));
        cls.setClassLoader(this);
        resolveSuperClass(cls);
        resolveInterfaces(cls);
        return cls;
    }

    private void link(Jclass cls) {
        verify(cls);
        prepare(cls);
    }

    private void resolveSuperClass(Jclass cls) throws IOException {
        if (!cls.getClassName().equals("java/lang/Object")) {
            cls.setSuperClass(cls.getClassLoader().loadClass(cls.getSuperClassName()));
        }
    }

    private void resolveInterfaces(Jclass cls) throws IOException {
        String[] interfaceNames = cls.getInterfaceNames();
        Jclass[] interfaces = new Jclass[interfaceNames.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = cls.getClassLoader().loadClass(interfaceNames[i]);
        }
        cls.setInterfaceClasses(interfaces);
    }

    private void verify(Jclass cls) {
    }

    private void prepare(Jclass cls) {
        calcInstanceFieldsSlotIds(cls);
        clacStaticFieldsSlotIds(cls);
        allocAndInitStaticVars(cls);
    }

    private void calcInstanceFieldsSlotIds(Jclass cls) {
        int slotId = 0;
        if (cls.getSuperClass() != null) {
            slotId += cls.getSuperClass().getInstanceFieldCount();
        }
        for (Field field : cls.getFields()) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId += 1;
                if (field.isLongOrDouble()) {
                    slotId += 1;
                }
            }
        }
        cls.setInstanceFieldCount(slotId);
    }

    private void clacStaticFieldsSlotIds(Jclass cls) {
        int slotId = 0;
        for (Field field : cls.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId += 1;
                if (field.isLongOrDouble()) {
                    slotId += 1;
                }
            }
        }
        cls.setStaticFieldCount(slotId);
    }

    private void allocAndInitStaticVars(Jclass cls) {
        cls.setStaticVars(new Object[cls.getStaticFieldCount()]);
        for (Field field: cls.getFields()) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(cls, field);
            }
        }
    }

    private void initStaticFinalVar(Jclass cls, Field field) {
        Object[] staticVars = cls.getStaticVars();
        ConstantPool cp = cls.getConstantPool();
        int index = field.getConstantValueIndex();
        if (index > 0) {
            if (field.getDescriptor().equals("Ljava/lang/String;")) {
                staticVars[field.getSlotId()] = Jstring.stringToInternObject(this, 
                        (String)cp.getConst(index));
            } else {
                staticVars[field.getSlotId()] = cp.getConst(index);
            }
        }
    }

    private void loadBasicClasses() throws IOException {        
        Jclass jlClassClass = this.loadClass("java/lang/Class");
        for (Jclass cls: classMap.values()) {
            cls.setClassObject(jlClassClass.newObject());
            cls.getClassObject().setExtra(cls);
        }
    }

    private void loadPrimitiveClasses() throws IOException {
        String[] types = new String[] {"void", "boolean", "byte", "short",
            "int", "long", "char", "float", "double"};
        
        for (String type: types) {
            Jclass cls = new Jclass(type, false);
            cls.setClassLoader(this);          
            cls.setClassObject(this.loadClass("java/lang/Class").newObject());
            cls.getClassObject().setExtra(cls);
            classMap.put(type, cls);
            System.out.printf("[Loaded %s]\n", cls.getClassName());
        }
    }
}
