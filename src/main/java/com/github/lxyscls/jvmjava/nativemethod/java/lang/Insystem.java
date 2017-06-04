package com.github.lxyscls.jvmjava.nativemethod.java.lang;


import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sk-xinyilong
 */
public final class Insystem {
    private Insystem() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/System", "registerNatives", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                }
            }
        );
    }
}
