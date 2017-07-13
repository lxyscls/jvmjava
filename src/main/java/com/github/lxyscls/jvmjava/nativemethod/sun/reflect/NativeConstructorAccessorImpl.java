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
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;

/**
 *
 * @author sk-xinyilong
 */
public class NativeConstructorAccessorImpl {
    private NativeConstructorAccessorImpl() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/reflect/NativeConstructorAccessorImpl", "newInstance0", 
                "(Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Ljava/lang/Object;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject ctorObj = frame.getLocalVars().getRef(0);
                    Jobject argArrObj = frame.getLocalVars().getRef(1);
                    
                    Jclass clzCls = ((Jobject)ctorObj.getRefVar("clazz", "Ljava/lang/Class;")).getExtra();
                    if (!clzCls.getInitStarted()) {
                        frame.revertNextPc();
                        clzCls.clInitClass(frame);
                        return;
                    }
                    
                    Jobject clzObj = clzCls.newObject();
                    Method ctor = null;
                    
                    for (Method c : clzCls.getPublicOnlyCtor(0)) {
                        if ("()V".equals(c.getDescriptor())) {
                            ctor = c;
                        }
                    }
                    
                    Method cloneCtor = ctor.clone();
                    cloneCtor.ctorAddAreturn();
                    
                    Frame newFrame = new Frame(frame.getThread(), cloneCtor);
                    frame.getThread().pushFrame(newFrame);
                    newFrame.getLocalVars().setRef(0, clzObj);
                }       
            }  
        );
    }
}
