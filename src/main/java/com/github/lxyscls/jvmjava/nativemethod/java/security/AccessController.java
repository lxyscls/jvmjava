/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.security;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;

/**
 *
 * @author sk-xinyilong
 */
public class AccessController {
    private AccessController() {}
    
    private static void helpFunc(Frame frame) {
        Jobject action = frame.getLocalVars().getRef(0);
        Method method = MethodLookup.lookupMethodInClass(action.getBelongClass(), "run", "()Ljava/lang/Object;");

        Object[] slots = new Object[method.getArgCount()-1];
        for (int i = slots.length-1; i >= 0; i--) {
            slots[i] = frame.getOperandStack().popObject();
        }

        Frame newFrame = new Frame(frame.getThread(), method);
        frame.getThread().pushFrame(newFrame);
        newFrame.getLocalVars().setObject(0, action);
        for (int i = 0; i < slots.length; i++) {
            newFrame.getLocalVars().setObject(i+1, slots[i]);
        }         
    }
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/security/AccessController", "doPrivileged", 
                "(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    helpFunc(frame);
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/security/AccessController", "doPrivileged", 
                "(Ljava/security/PrivilegedAction;)Ljava/lang/Object;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    helpFunc(frame);
                }
            }
        );      
        NativeMethod.registerNativeMethod("java/security/AccessController", "getStackAccessControlContext", 
                "()Ljava/security/AccessControlContext;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    frame.getOperandStack().pushRef(null);
                }
            }
        );         
    }    
}
