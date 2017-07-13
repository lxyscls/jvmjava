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
public class ClassLoader$NativeLibrary {
    private ClassLoader$NativeLibrary() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/ClassLoader$NativeLibrary", "load", "(Ljava/lang/String;Z)V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject libObj = frame.getLocalVars().getRef(0); // this
                    libObj.setRefVar("loaded", "Z", true);
                }
            }
        );       
    }
}
