/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class ANewArray extends Index16ByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        
        ClassRef cr = (ClassRef)frame.getMethod().getBelongClass()
                .getConstantPool().getConst(index);
        try {
            Jclass compCls = cr.resolvedClass();
            stack.pushRef(compCls.newArrayClass().newArray(count));
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
    
}
