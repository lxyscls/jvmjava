/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.convert;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class F2i extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt((int)stack.popFloat());     
    }
}

class F2l extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushLong((long)stack.popFloat());     
    }
}

class F2d extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.pushDouble((double)stack.popFloat());     
    }
}