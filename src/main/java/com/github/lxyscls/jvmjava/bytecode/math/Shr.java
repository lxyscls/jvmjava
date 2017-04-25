/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.math;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class Ishr extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        stack.pushInt(value1 >> (0x1F & value2));
    }
}

class Iushr extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        if (value1 < 0) {
            stack.pushInt((value1 >> (0x1F & value2)) + (2 << ~(0x1F & value2)));
        } else if (value1 > 0) {
            stack.pushInt(value1 >> (0x1F & value2));
        } else {
            stack.pushInt(value1);
        }
    }
}

class Lshr extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        long value1 = stack.popLong();
        stack.pushLong(value1 >> (0x3F & value2));
    }
}

class Lushr extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        long value1 = stack.popLong();
        if (value1 < 0) {
            stack.pushLong((value1 >> (0x3F & value2)) + (2L << ~(0x1F & value2)));
        } else if (value1 > 0) {
            stack.pushLong(value1 >> (0x1F & value2));
        } else {
            stack.pushLong(value1);
        }
    }
}

