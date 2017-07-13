/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;

/**
 *
 * @author sk-xinyilong
 */
public class Nstring {
    private Nstring() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/String", "intern", "()Ljava/lang/String;", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject strObj = frame.getLocalVars().getRef(0);
                    Jobject istrObj = Jstring.stringToInternObject(
                            frame.getMethod().getBelongClass().getClassLoader(), 
                            Jstring.internObjectToString(strObj)
                    );
                    frame.getOperandStack().pushRef(istrObj);
                }
            }
        );
    }
}
