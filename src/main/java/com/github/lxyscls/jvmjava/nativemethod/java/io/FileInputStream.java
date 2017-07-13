/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.io;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class FileInputStream {
    private FileInputStream() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/io/FileInputStream", "initIDs", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                }
            }
        );      
    }       
}
