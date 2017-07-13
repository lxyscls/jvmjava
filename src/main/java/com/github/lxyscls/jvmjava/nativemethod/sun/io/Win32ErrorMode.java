/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.sun.io;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class Win32ErrorMode {
    private Win32ErrorMode() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/io/Win32ErrorMode", "setErrorMode", "(J)J",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    frame.getOperandStack().pushLong(0L);
                }
            }
        );        
    }       
}
