/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.sun.misc;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class Signal {
    private Signal() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/misc/Signal", "findSignal", "(Ljava/lang/String;)I", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    frame.getOperandStack().pushInt(0);
                }
            }
        );
        NativeMethod.registerNativeMethod("sun/misc/Signal", "handle0", "(IJ)J", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    frame.getOperandStack().pushLong(0L);
                }
            }
        );        
    }
}
