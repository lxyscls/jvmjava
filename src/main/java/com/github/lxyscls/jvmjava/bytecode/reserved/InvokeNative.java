/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reserved;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;

/**
 *
 * @author sk-xinyilong
 */
public class InvokeNative extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        Jclass cls = method.getBelongClass();
        
        NativeMethod nmethod = NativeMethod.findNativeMethod(cls.getClassName(),
                method.getName(), method.getDescriptor());
        if (nmethod == null) {
            throw new UnsatisfiedLinkError();
        }
        nmethod.func(frame);
    }
    
}
