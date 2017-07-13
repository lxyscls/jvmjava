/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import com.github.lxyscls.jvmjava.classfile.ClassFile;
import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.SourceFileAttributeInfo;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.AccessFlags;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    private String sourceFile;
    
    private boolean initStarted;
    private Jobject jClass;

    
    public Jclass(String name, boolean isArray) {
        accessFlags = AccessFlags.ACC_PUBLIC;
        className = name;
        if (isArray) {
            superClassName = "java/lang/Object";
            interfaceNames = new String[]{"java/lang/Cloneable", "java/io/Serializable"};
        } else {
            superClassName = null;
            interfaceNames = null;
        }
        
        cp = null;
        fields = new Field[0];
        methods = new Method[0];
        superClass = null;
        interfaces = new Jclass[0];
        initStarted = true;
    }
    
    public Jclass(ClassFile cf) {
        accessFlags = cf.getAccessFlags();
        className = cf.getClassName();
        superClassName = cf.getSuperClassName();
        interfaceNames = cf.getInterfaceNames();
        
        cp = new ConstantPool(this, cf.getConstantPool());
        
        MemberInfo[] fieldInfos = cf.getFields();
        fields = new Field[fieldInfos.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(this, fieldInfos[i]);
        }
        
        MemberInfo[] methodInfos = cf.getMethods();
        methods = new Method[methodInfos.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Method(this, methodInfos[i]);
        }
        
        sourceFile = getSourceFile(cf);

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
    
    public int getAccessFlags() {
        return this.accessFlags;
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
    
    public Method[] getMethods() {
        return this.methods;
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
                    method.getDescriptor().equals("([Ljava/lang/String;)V")) {
                return method;
            }
        }
        return null;
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
    
    public boolean isSubClassOf(Jclass cls) {
        for (Jclass _cls = this.getSuperClass(); _cls != null; _cls = _cls.getSuperClass()) {
            if (_cls == cls) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSubInterfaceOf(Jclass cls) {
        for (Jclass _cls : this.getInterfaceClasses()) {
            if (_cls == cls || _cls.isSubInterfaceOf(cls)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isImplements(Jclass cls) {
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
            return cls.isSubClassOf(this);
        } else {
            return cls.isImplements(this);
        }
    }
    
    public boolean isSuperClassof(Jclass cls) {
        return cls.isSubClassOf(this);
    }
    
    public boolean getInitStarted() {
        return this.initStarted;
    }
    
    public void setInitStarted() {
        this.initStarted = true;
    }
    
    public void clInitClass(Frame frame) {
        this.initStarted = true;
        Method clinit = MethodLookup.lookupMethodInClass(this, "<clinit>", "()V");
        if (clinit != null && clinit.getBelongClass() == this) {
            Frame newFrame = new Frame(frame.getThread(), clinit);
            frame.getThread().pushFrame(newFrame);
        }
        if (!this.isInterface()) {
            if (superClass != null && !superClass.getInitStarted()) {
                superClass.clInitClass(frame);
            }
        }
    }
        
    public Jobject newObject() {
        return initInstanceFields(new Jobject(this, instanceFieldCount));
    }
    
    private Jobject initInstanceFields(Jobject obj) {
        for (Jclass cls = this; cls != null; cls = cls.getSuperClass()) {
            for (Field field : cls.getFields()) {
                if (!field.isStatic()) {
                    switch (field.getDescriptor().charAt(0)) {
                        case 'L': case '[':
                            obj.getFields()[field.getSlotId()] = null;
                            break;
                        case 'B': case 'C': case 'S': case 'I':
                            obj.getFields()[field.getSlotId()] = 0;
                            break;
                        case 'J':
                            obj.getFields()[field.getSlotId()] = 0L;
                            break;                            
                        case 'Z':
                            obj.getFields()[field.getSlotId()] = false;
                            break;
                        case 'F': case 'D':
                            obj.getFields()[field.getSlotId()] = 0.0;
                            break;
                    }
                }
            }
        }
        return obj;
    }
    
    public Jobject newArray(int count) {
        switch (getClassName()) {
            case "[B": return new Jobject(this, new Byte[count]);
            case "[C": return new Jobject(this, new Character[count]);
            case "[D": return new Jobject(this, new Double[count]);
            case "[F": return new Jobject(this, new Float[count]);
            case "[I": return new Jobject(this, new Integer[count]);
            case "[J": return new Jobject(this, new Long[count]);
            case "[S": return new Jobject(this, new Short[count]);
            case "[Z": return new Jobject(this, new Byte[count]); // Share with Byte
            default: return new Jobject(this, new Jobject[count]);
        }
    }
    
    public Jclass newArrayClass() throws IOException {
        return loader.loadClass(getArrayClassName());
    }
    
    private String getArrayClassName() {
        if (getClassName().startsWith("[")) {
            return "[" + getClassName();
        }
        
        switch (getClassName()) {
            case "void": return "[V";
            case "boolean": return "[Z";
            case "byte": return "[B";
            case "short": return "[S";
            case "int": return "[I";
            case "long": return "[J";
            case "char": return "[C";
            case "float": return "[F";
            case "double": return "[D";
        }
        return "[L" + getClassName() + ";";
    }
    
    public Jobject newMultiDimensionalArray(int[] counts) throws IOException {
        Jobject arrRef = newArray(counts[0]);
        if (counts.length > 1) {
            Jobject[] array = (Jobject[])arrRef.getArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = newComponentClass().newMultiDimensionalArray(
                        Arrays.copyOfRange(counts, 1, counts.length));
            }
        }
        return arrRef;
    }

    public Jclass newComponentClass() throws IOException {
        return loader.loadClass(getComponentClassName());
    }

    public String getComponentClassName() {
        String subName = getClassName().substring(1);
        if (subName.startsWith("L")) {
            return subName.substring(1, subName.length()-1);
        }
        
        switch (subName) {
            case "V": return "void";
            case "Z": return "boolean";
            case "B": return "byte";
            case "S": return "short";
            case "I": return "int";
            case "J": return "long";
            case "C": return "char";
            case "F": return "float";
            case "D": return "double";
        }
        return subName;
    }
    
    public Field getInstanceField(String name, String descriptor) {
        for (Jclass cls = this; cls != null; cls = cls.superClass) {
            for (Field field : cls.getFields()) {
                if (!field.isStatic() && name.equals(field.getName()) 
                        && descriptor.equals(field.getDescriptor())) {
                    return field;
                }
            }
        }
        return null;
    }
    
    public void setClassObject(Jobject cobj) {
        this.jClass = cobj;
    }
    
    public Jobject getClassObject() {
        return this.jClass;
    }
    
    public String getSourceFile() {
        return this.sourceFile;
    }

    private String getSourceFile(ClassFile cf) {
        for (AttributeInfo attr : cf.getAttributes()) {
            if (attr instanceof SourceFileAttributeInfo) {
                return ((SourceFileAttributeInfo) attr).getFileName();
            }
        }
        return "Unknown";
    }
    
    public boolean isPrimitive() {
        return "void".equals(className) || "boolean".equals(className) || 
                "byte".equals(className) || "short".equals(className) || 
                "int".equals(className) || "long".equals(className) || 
                "char".equals(className) || "float".equals(className) || 
                "double".equals(className);
    }
    
    // reflect
    public Method[] getPublicOnlyCtor(int publicOnly) {
        boolean pub = (publicOnly == 1);
        List<Method> list = new LinkedList<>();
        for (Method method : methods) {
            if ("<init>".equals(method.getName()) && !method.isStatic() && 
                    (!pub || method.isPublic())) {
                list.add(method);
            }
        }
        return list.toArray(new Method[0]);
    }    
    
    // reflect
    public Field[] getPublicOnlyField(int publicOnly) {
        if (publicOnly == 1) {
            List<Field> list = new LinkedList<>();
            for (Field field : fields) {
                if (field.isPublic()) {
                    list.add(field);
                }
            }
            return list.toArray(new Field[0]);
        } else {
            return fields;
        }
    }    
    
    public static String toClassName(String descriptor) {
        switch (descriptor.charAt(0)) {
            case '[': return descriptor;
            case 'L':
                return descriptor.substring(1, descriptor.length()-1);
            case 'V': return "void";
            case 'Z': return "boolean";
            case 'B': return "byte";
            case 'S': return "short";
            case 'I': return "int";
            case 'J': return "long";
            case 'C': return "char";
            case 'F': return "float";
            case 'D': return "double";
            default: return descriptor;
        }
    }    
    
    public static Jobject toClassArr(ClassLoader cl, Jclass[] clss) throws IOException {
        Jclass caCls = cl.loadClass("java/lang/Class").newArrayClass();
        Jobject caArr = caCls.newArray(clss == null ? 0 : clss.length);
        
        for (int i = 0; i < caArr.getArrayLength(); i++) {
            caArr.getArray()[i] = clss[i].getClassObject();
        }
        return caArr;
    }
}
