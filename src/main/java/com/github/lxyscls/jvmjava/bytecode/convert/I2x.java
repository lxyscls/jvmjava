/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.convert;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import java.math.BigInteger;

/**
 *
 * @author sk-xinyilong
 */
class I2l extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushLong((long)stack.popInt());
    }
}

class I2f extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushFloat((float)stack.popInt());
    }
}

class I2d extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushDouble((double)stack.popInt());
    }
}

class I2b extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(stack.popInt() & 0xFF);
    }
}

class I2c extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value = stack.popInt();
        
        BigInteger bi = new BigInteger(String.valueOf(value));
        byte[] ba = bi.toByteArray();
        ba[0] = 0;
        ba[1] = 0;
        BigInteger bbi = new BigInteger(ba);
        stack.pushInt(bbi.intValue());
    }
}

class I2s extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(stack.popInt() & 0xFFFF);
    }
}