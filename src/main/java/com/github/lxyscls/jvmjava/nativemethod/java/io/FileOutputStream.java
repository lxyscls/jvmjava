/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.io;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import java.util.Arrays;

/**
 *
 * @author sk-xinyilong
 */
public class FileOutputStream {
    private FileOutputStream() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/io/FileOutputStream", "writeBytes", "([BIIZ)V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject fos = frame.getLocalVars().getRef(0);
                    Byte[] ab = (Byte[])frame.getLocalVars().getRef(1).getArray();
                    int off = frame.getLocalVars().getInt(2);
                    int len = frame.getLocalVars().getInt(3);
                    
                    StringBuilder sb = new StringBuilder();
                    for (int i = off; i < off+len; i++) {
                        sb.append((char)(byte)ab[i]);
                    }
                    
                    System.out.print(sb);
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/io/FileOutputStream", "initIDs", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                }
            }
        );            
    }    
}
