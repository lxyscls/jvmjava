/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import com.github.lxyscls.jvmjava.classfile.ClassFile;
import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.AccessFlags;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
public class Jclass {
    private final int accessFlags;
    private final String className;
    private final String superClassName;
    private final String[] interfaceNames;
    private final ConstantPool cp;
    private final Field[] fields;
    private final Method[] methods;
    
    private ClassLoader loader;
    private Jclass superClass;
    private Jclass[] interfaces;
    private int instanceFieldCount;
    private int staticFieldCount;
    private Object[] staticVars;
    
    public Jclass(ClassFile cf) {
        accessFlags = cf.getAccessFlags();
        className = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfaceNames = cf.getInterfaceNames();
        
        MemberInfo[] fieldInfos = cf.getFields();
        fields = new Field[fieldInfos.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(fieldInfos[i]);
            fields[i].setClass(this);
        }
        
        MemberInfo[] methodInfos = cf.getMethods();
        methods = new Method[methodInfos.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Method(methodInfos[i]);
            methods[i].setClass(this);
        }

        cp = new ConstantPool(this, cf.getConstantPool());
        
        superClass = null;
        interfaces = null;
        instanceFieldCount = 0;
        staticFieldCount = 0;
        staticVars = null;
    }
    
    public boolean isPublic() {
        return (accessFlags & AccessFlags.ACC_PUBLIC) != 0;
    }
    
    public boolean isFinal() {
        return (accessFlags & AccessFlags.ACC_FINAL) != 0;
    }
    
    public boolean isSuper() {
        return (accessFlags & AccessFlags.ACC_SUPER) != 0;
    }
    
    public boolean isInterface() {
        return (accessFlags & AccessFlags.ACC_INTERFACE) != 0;
    }
    
    public boolean isAbstract() {
        return (accessFlags & AccessFlags.ACC_ABSTRACT) != 0;
    }
    
    public boolean isSynthetic() {
        return (accessFlags & AccessFlags.ACC_SYNTHETIC) != 0;
    }
    
    public boolean isAnnotation() {
        return (accessFlags & AccessFlags.ACC_ANNOTATION) != 0;
    }
    
    public boolean isEnum() {
        return (accessFlags & AccessFlags.ACC_ENUM) != 0;
    }
       
    public String getClassName() {
        return this.className;
    }
        
    public ClassLoader getClassLoader() {
        return this.loader;
    }
    
    public void setClassLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public String getSuperClassName() {
        return this.superClassName;
    }
    
    public Jclass getSuperClass() {
        return this.superClass;
    }
    
    public void setSuperClass(Jclass superClass) {
        this.superClass = superClass;
    }
    
    public String[] getInterfaceNames() {
        return this.interfaceNames;
    }
    
    public Jclass[] getInterfaceClasses() {
        return this.interfaces;
    }
    
    public void setInterfaceClasses(Jclass[] interfaces) {
        this.interfaces = interfaces;
    }
    
    public int getInstanceFieldCount() {
        return this.instanceFieldCount;
    }
    
    public void setInstanceFieldCount(int instanceFieldCount) {
        this.instanceFieldCount = instanceFieldCount;
    }
    
    public int getStaticFieldCount() {
        return this.staticFieldCount;
    }
    
    public void setStaticFieldCount(int staticFieldCount) {
        this.staticFieldCount = staticFieldCount;
    }
    
    public Field[] getFields() {
        return this.fields;
    }
    
    public Object[] getStaticVars() {
        return this.staticVars;
    }
    
    public void setStaticVars(Object[] staticVars) {
        this.staticVars = staticVars;
    }
    
    public ConstantPool getConstantPool() {
        return this.cp;
    }
    
    public Method getMainMethod() {
        for (Method method : methods) {
            if (method.isStatic() && method.getName().equals("main") &&
                    method.getDescriptor().equals("(Ljava/lang/String;)V")) {
                return method;
            }
        }
        return null;
    }
    
    public Jobject newObject() {
        return new Jobject(this, instanceFieldCount);
    }

    public boolean isAccessibleTo(Jclass cls) {
        return isPublic() || getPackageName().equals(cls.getPackageName());
    }

    public String getPackageName() {
        int endIndex = this.className.lastIndexOf('/');
        if (endIndex >= 0) {
            return this.className.substring(0, endIndex);
        }
        return "";
    }
    
    public boolean isSubclassOf(Jclass cls) {
        for (Jclass _cls = this.getSuperClass(); _cls != null; _cls = _cls.getSuperClass()) {
            if (_cls == cls) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isSubInterfaceOf(Jclass cls) {
        for (Jclass _cls : this.getInterfaceClasses()) {
            if (_cls == cls || _cls.isSubInterfaceOf(cls)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isImplements(Jclass cls) {
        for (Jclass _cls = this; _cls != null; _cls = _cls.getSuperClass()) {
            if (_cls.isSubInterfaceOf(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAssignableFrom(Jclass cls) {
        if (cls == this) {
            return true;
        }
        
        if (!isInterface()) {
            return cls.isSubclassOf(this);
        } else {
            return cls.isImplements(this);
        }
    }
}
