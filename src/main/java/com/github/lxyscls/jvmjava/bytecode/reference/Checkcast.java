/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class Checkcast extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Jobject ref = (Jobject)stack.getTop();
        if (ref == null) {
            return;
        }
        
        ClassRef cr = (ClassRef)frame.getMethod().getBelongClass().getConstantPool().getConst(index);
        try {
            Jclass cls = cr.resolvedClass();
            if (!ref.isInstanceOf(cls)) {
                throw new ClassCastException();
            }
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}
