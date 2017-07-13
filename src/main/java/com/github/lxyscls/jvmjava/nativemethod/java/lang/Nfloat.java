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
public class Nfloat {
    private Nfloat() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Float", "floatToRawIntBits", "(F)I",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    float f = frame.getLocalVars().getFloat(0);
                    frame.getOperandStack().pushInt(Float.floatToRawIntBits(f));
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/lang/Float", "intBitsToFloat", "(I)F",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    int i = frame.getLocalVars().getInt(0);
                    frame.getOperandStack().pushFloat(Float.intBitsToFloat(i));
                }
            }
        );          
    }       
}
