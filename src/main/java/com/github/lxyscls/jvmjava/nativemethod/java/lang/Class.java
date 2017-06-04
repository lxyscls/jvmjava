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
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public final class Class {
    private Class() {}

    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Class", "registerNatives", "()V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
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
                    
                    NativeMethod.registerNativeMethod("java/lang/Class", "desiredAssertionStatus0",
                            "(Ljava/lang/Class;)Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(0);
                            }                            
                        }
                    );                    
                }
            }
        );
    }    
}
