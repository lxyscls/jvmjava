/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.sun.reflect;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;

/**
 *
 * @author sk-xinyilong
 */
public class Reflection {
    private Reflection() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/reflect/Reflection", "getCallerClass", "()Ljava/lang/Class;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Frame callerFrame = frame.getThread().getFrames()[2];
                    Jclass callerClass = callerFrame.getMethod().getBelongClass();
                    frame.getOperandStack().pushRef(callerClass.getClassObject());
                }
            }
        );  
        NativeMethod.registerNativeMethod("sun/reflect/Reflection", "getClassAccessFlags", "(Ljava/lang/Class;)I",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject clsObj = frame.getLocalVars().getRef(0);
                    frame.getOperandStack().pushInt(clsObj.getExtra().getAccessFlags());
                }
            }
        );        
    }      
}
