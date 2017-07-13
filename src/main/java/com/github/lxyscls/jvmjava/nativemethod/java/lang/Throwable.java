/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.Jthread;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.util.Arrays;

/**
 *
 * @author sk-xinyilong
 */
public final class Throwable {
    private Throwable() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/Throwable", "fillInStackTrace",
                "(I)Ljava/lang/Throwable;", 
                new NativeMethod() {
                    @Override
                    public void func(Frame frame) {
                        Jobject ex = frame.getLocalVars().getRef(0);
                        // use filed "array" for hack
                        ex.setArray(createStackTraceElements(ex, frame.getThread()));
                        frame.getOperandStack().pushRef(ex);
                    }
                }
        );
    }
    
    private static StackTraceElement[] createStackTraceElements(Jobject ex, Jthread thread) {
        int skip = distanceToObject(ex.getBelongClass()) + 2;
        Frame[] frames = thread.getFrames();
        frames = Arrays.copyOfRange(frames, skip, frames.length);
        StackTraceElement[] stes = new StackTraceElement[frames.length];
        
        for (int i = 0; i < stes.length; i++) {
            stes[i] = new StackTraceElement(
                    frames[i].getMethod().getBelongClass().getClassName().replace("/", "."), 
                    frames[i].getMethod().getName(), 
                    frames[i].getMethod().getBelongClass().getSourceFile(),
                    frames[i].getMethod().getLineNumber(frames[i].getNextPc()-1));
        }
        return stes;
    }
    
    private static int distanceToObject(Jclass exCls) {
        int skip = 0;
        for (Jclass cls = exCls.getSuperClass(); cls != null; cls = cls.getSuperClass()) {
            skip += 1;
        }
        return skip;
    }
}
