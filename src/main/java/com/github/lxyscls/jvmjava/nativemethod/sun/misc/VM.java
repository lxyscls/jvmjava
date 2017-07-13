/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.sun.misc;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class VM {
    private VM() {};
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/misc/VM", "initialize", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    try {
                        ClassLoader cl = frame.getMethod().getBelongClass().getClassLoader();
                        Jclass sysCls = cl.loadClass("java/lang/System");
                        Method initSysMt = MethodLookup.lookupMethodInClass(
                                sysCls, "initializeSystemClass", "()V");
                        
                        if (initSysMt != null) {
                            Frame newFrame = new Frame(frame.getThread(), initSysMt);
                            frame.getThread().pushFrame(newFrame);
                            for (int i = initSysMt.getArgCount()-1; i >= 0; i--) {
                                newFrame.getLocalVars().setObject(i, frame.getOperandStack().popObject());
                            }  
                        }
                    } catch (IOException ex) {
                        System.err.println(ex);
                        System.exit(-1);
                    }
                }
            }
        );
    }    
}
