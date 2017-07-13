/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Field;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public final class Nclass {
    private Nclass() {}

    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Class", "registerNatives", "()V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {     
                    NativeMethod.registerNativeMethod("java/lang/Class", "getName0", "()Ljava/lang/String;",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject clsobj = frame.getLocalVars().getRef(0);
                                Jclass cls = clsobj.getExtra();
                                frame.getOperandStack().pushRef(
                                    Jstring.stringToInternObject(
                                        cls.getClassLoader(), cls.getClassName().replace('/', '.')
                                        )
                                );
                            }                            
                        }
                    );
                    NativeMethod.registerNativeMethod("java/lang/Class", "getModifiers", "()I",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject clsObj = frame.getLocalVars().getRef(0);
                                frame.getOperandStack().pushInt(clsObj.getExtra().getAccessFlags());
                            }                            
                        }
                    ); 
                    NativeMethod.registerNativeMethod("java/lang/Class", "getSuperclass", "()Ljava/lang/Class;",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject clsObj = frame.getLocalVars().getRef(0);
                                Jclass superCls = clsObj.getExtra().getSuperClass();
                                if (superCls != null) {
                                    frame.getOperandStack().pushObject(superCls.getClassObject());
                                } else {
                                    frame.getOperandStack().pushObject(null);
                                }
                            }                            
                        }
                    );                      
                    NativeMethod.registerNativeMethod("java/lang/Class", "desiredAssertionStatus0",
                            "(Ljava/lang/Class;)Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(0);
                            }                            
                        }
                    ); 
                    NativeMethod.registerNativeMethod("java/lang/Class", "isPrimitive",
                            "()Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(0);
                                if (obj.getExtra().isPrimitive()) {
                                    frame.getOperandStack().pushInt(1);
                                } else {
                                    frame.getOperandStack().pushInt(0);
                                }
                            }                            
                        }
                    );   
                    NativeMethod.registerNativeMethod("java/lang/Class", "isInterface",
                            "()Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(0);
                                if (obj.getExtra().isInterface()) {
                                    frame.getOperandStack().pushInt(1);
                                } else {
                                    frame.getOperandStack().pushInt(0);
                                }
                            }                            
                        }
                    );                    
                    NativeMethod.registerNativeMethod("java/lang/Class", "getDeclaredFields0",
                            "(Z)[Ljava/lang/reflect/Field;",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                try {
                                    ClassLoader cl = frame.getMethod().getBelongClass().getClassLoader();
                                    
                                    Jobject clsObj = frame.getLocalVars().getRef(0);
                                    int publicOnly = frame.getLocalVars().getInt(1);
                                    
                                    Field[] fields = clsObj.getExtra().getPublicOnlyField(publicOnly);
                                    Jclass fieldCls = cl.loadClass("java/lang/reflect/Field");
                                    Jobject fieldArr = fieldCls.newArrayClass().newArray(fields.length);
                                    frame.getOperandStack().pushRef(fieldArr);
                                    
                                    if (fieldArr.getArrayLength() > 0) {
                                        Method fieldCtor = MethodLookup.lookupMethodInClass(fieldCls, "<init>", 
                                                "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;IILjava/lang/String;[B)V");
                                        for (int i = 0; i < fieldArr.getArrayLength(); i++) {
                                            fieldArr.getArray()[i] = fieldCls.newObject();
                                            Frame newFrame = new Frame(frame.getThread(), fieldCtor);
                                            frame.getThread().pushFrame(newFrame);
                                            newFrame.getLocalVars().setRef(0, (Jobject)fieldArr.getArray()[i]); // this
                                            newFrame.getLocalVars().setRef(1, clsObj); // declaringClass
                                            newFrame.getLocalVars().setRef(2, Jstring.stringToInternObject(cl, fields[i].getName())); // name
                                            newFrame.getLocalVars().setRef(3, fields[i].getTypeClass().getClassObject()); // type
                                            newFrame.getLocalVars().setInt(4, fields[i].getAccessFlags()); // modifiers
                                            newFrame.getLocalVars().setInt(5, fields[i].getSlotId()); // slot
                                            newFrame.getLocalVars().setRef(6, Jstring.stringToInternObject(cl, fields[i].getSignature())); // signature
                                            newFrame.getLocalVars().setRef(7, cl.loadClass("byte").newArrayClass().newArray(fields[i].getAnnotationData().length)); // annotations
                                        }
                                    }
                                } catch (IOException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }                            
                        }
                    ); 
                    NativeMethod.registerNativeMethod("java/lang/Class", "getDeclaredConstructors0",
                            "(Z)[Ljava/lang/reflect/Constructor;",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                try {
                                    ClassLoader cl = frame.getMethod().getBelongClass().getClassLoader();
                                    
                                    Jobject clsObj = frame.getLocalVars().getRef(0);
                                    // 'not' publicOnly: DECLARED
                                    int publicOnly = frame.getLocalVars().getInt(1);
                                    
                                    Method[] ctors = clsObj.getExtra().getPublicOnlyCtor(publicOnly);
                                    Jclass  ctorCls = cl.loadClass("java/lang/reflect/Constructor");
                                    Jobject ctorArr = ctorCls.newArrayClass().newArray(ctors.length);
                                    frame.getOperandStack().pushRef(ctorArr);
                                    
                                    if (ctorArr.getArrayLength() > 0) {
                                        Method ctorCtor = MethodLookup.lookupMethodInClass(ctorCls, "<init>", 
                                                "(Ljava/lang/Class;[Ljava/lang/Class;[Ljava/lang/Class;IILjava/lang/String;[B[B)V");
                                        for (int i = 0; i < ctorArr.getArrayLength(); i++) {
                                            ctorArr.getArray()[i] = ctorCls.newObject();
                                            Frame newFrame = new Frame(frame.getThread(), ctorCtor);
                                            frame.getThread().pushFrame(newFrame);
                                            newFrame.getLocalVars().setRef(0, (Jobject)ctorArr.getArray()[i]); // this
                                            newFrame.getLocalVars().setRef(1, clsObj); // declaringClass
                                            newFrame.getLocalVars().setObject(2, Jclass.toClassArr(cl, ctors[i].getArgTypes())); // parameterTypes
                                            newFrame.getLocalVars().setObject(3, Jclass.toClassArr(cl, ctors[i].getExceptionTypes())); // checkedExceptions
                                            newFrame.getLocalVars().setInt(4, ctors[i].getAccessFlags()); // modifiers
                                            newFrame.getLocalVars().setInt(5, 0); // slot
                                            newFrame.getLocalVars().setRef(6, Jstring.stringToInternObject(cl, ctors[i].getSignature())); // signature
                                            newFrame.getLocalVars().setRef(7, cl.loadClass("byte").newArrayClass().newArray(ctors[i].getAnnotationData().length)); // annotations
                                            newFrame.getLocalVars().setRef(8, cl.loadClass("byte").newArrayClass().newArray(ctors[i].getArgAnnotationData().length)); // parameterAnnotations                                            
                                        }
                                    }
                                } catch (IOException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }                            
                        }
                    );                     
                }
            }
        );
        NativeMethod.registerNativeMethod("java/lang/Class", "getPrimitiveClass", 
                "(Ljava/lang/String;)Ljava/lang/Class;", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject jstr = frame.getLocalVars().getRef(0);
                    String clsname = Jstring.internObjectToString(jstr);
                    ClassLoader loader = frame.getMethod().getBelongClass().getClassLoader();
                    try {
                        Jobject clsobj = loader.loadClass(clsname).getClassObject();
                        frame.getOperandStack().pushRef(clsobj);
                    } catch (IOException ex) {
                        System.err.println(ex);
                        System.exit(-1);
                    }
                }
            }
        );
        NativeMethod.registerNativeMethod("java/lang/Class", "forName0", 
                "(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    try {
                        String name = Jstring.internObjectToString(frame.getLocalVars().getRef(0));
                        int initialize = frame.getLocalVars().getInt(1);
                        
                        Jclass cls = frame.getMethod().getBelongClass().getClassLoader().loadClass(name.replace(".", "/"));
                        if (initialize == 1&& !cls.getInitStarted()) {
                            frame.revertNextPc();
                            cls.clInitClass(frame);
                        } else {
                            frame.getOperandStack().pushRef(cls.getClassObject());
                        }
                    } catch (IOException ex) {
                        System.err.println(ex);
                        System.exit(-1);
                    }
                }
            }
        );        
    }    
}
