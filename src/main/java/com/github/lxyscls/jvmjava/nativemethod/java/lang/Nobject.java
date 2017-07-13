/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public final class Nobject {
    private Nobject() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Object", "registerNatives", "()V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    NativeMethod.registerNativeMethod("java/lang/Object", "getClass", "()Ljava/lang/Class;", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(0);
                                Jobject clsobj = obj.getBelongClass().getClassObject();
                                frame.getOperandStack().pushRef(clsobj);
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("java/lang/Object", "hashCode", "()I", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(0);
                                frame.getOperandStack().pushInt(obj.hashCode());
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("java/lang/Object", "notifyAll", "()V", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                            }
                        }
                    );                      
                    NativeMethod.registerNativeMethod("java/lang/Object", "clone", "()Ljava/lang/Object;", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                try {
                                    Jobject obj = frame.getLocalVars().getRef(0);
                                    Jclass cls = obj.getBelongClass().getClassLoader().loadClass("java/lang/Cloneable");
                                    
                                    if (!obj.getBelongClass().isImplements(cls)) {
                                        throw new CloneNotSupportedException();
                                    }
                                    
                                    frame.getOperandStack().pushObject(obj.clone());
                                } catch (IOException | CloneNotSupportedException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    );                     
                }
            }
        );
    }
}
