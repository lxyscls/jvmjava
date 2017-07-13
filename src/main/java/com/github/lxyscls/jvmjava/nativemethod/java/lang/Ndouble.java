/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class Ndouble {
    private Ndouble() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Double", "doubleToRawLongBits", "(D)J",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    double d = frame.getLocalVars().getDouble(0);
                    frame.getOperandStack().pushLong(Double.doubleToRawLongBits(d));
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/lang/Double", "longBitsToDouble", "(J)D",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    long l = frame.getLocalVars().getLong(0);
                    frame.getOperandStack().pushDouble(Double.longBitsToDouble(l));
                }
            }
        );          
    }     
}
