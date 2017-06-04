/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
public final class Object {
    private Object() {}
    
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
                }
            }
        );
    }
}
