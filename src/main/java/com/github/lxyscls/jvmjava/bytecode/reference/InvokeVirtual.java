/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodRef;

/**
 *
 * @author sk-xinyilong
 */
public class InvokeVirtual extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getBelongClass().getConstantPool();
        MethodRef ref = (MethodRef)cp.getConst(index);
        if (ref.getName().equals("println")) {
            OperandStack stack = frame.getOperandStack();
            switch (ref.getDescriptor()) {
                case "(Z)V": System.out.println(stack.popInt() != 0); break;
                case "(C)V": System.out.println(stack.popInt()); break;
                case "(B)V": System.out.println(stack.popInt()); break;
                case "(S)V": System.out.println(stack.popInt()); break;
                case "(I)V": System.out.println(stack.popInt()); break;
                case "(J)V": System.out.println(stack.popLong()); break;
                case "(F)V": System.out.println(stack.popFloat()); break;
                case "(D)V": System.out.println(stack.popDouble()); break;
                default: throw new NoSuchMethodError(ref.getDescriptor());
            }
            stack.popRef();
        }
    }
}
