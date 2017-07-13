/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class Thread {
    private Thread() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Thread", "registerNatives", "()V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    NativeMethod.registerNativeMethod("java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                try {
                                    ClassLoader cl = frame.getMethod().getBelongClass().getClassLoader();
                                    
                                    Jclass tCls = cl.loadClass("java/lang/Thread");
                                    Jobject tObj = tCls.newObject();
                                    tObj.setRefVar("group", "Ljava/lang/ThreadGroup;", 
                                            cl.loadClass("java/lang/ThreadGroup").newObject());
                                    tObj.setRefVar("priority", "I", 1);
                                    
                                    frame.getOperandStack().pushRef(tObj);
                                } catch (IOException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    );   
                    NativeMethod.registerNativeMethod("java/lang/Thread", "setPriority0", "(I)V", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("java/lang/Thread", "isAlive", "()Z", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(0);
                            }
                        }
                    ); 
                    NativeMethod.registerNativeMethod("java/lang/Thread", "start0", "()V", 
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                            }
                        }
                    );                      
                }
            }
        );
    }
}
