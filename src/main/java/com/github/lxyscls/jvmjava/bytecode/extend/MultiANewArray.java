/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.extend;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class MultiANewArray extends ByteCode {
    private int index;
    private short dimensions;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUint16();
        dimensions = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        ClassRef cr = (ClassRef)frame.getMethod().getBelongClass()
                .getConstantPool().getConst(index);
        try {
            Jclass arrCls = cr.resolvedClass();
            OperandStack stack = frame.getOperandStack();
            int[] counts = getAndCheckCounts(stack, dimensions);
            stack.pushRef(arrCls.newMultiDimensionalArray(counts));
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }

    private static int[] getAndCheckCounts(OperandStack stack, short dimensions) {
        int[] counts = new int[dimensions];
        for (int i = counts.length-1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }
        return counts;
    }
    
}
