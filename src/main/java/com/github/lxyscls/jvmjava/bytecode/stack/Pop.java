/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.stack;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
class Pop extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        if (!(frame.getOperandStack().getTop() instanceof Long ||
                frame.getOperandStack().getTop() instanceof Double)) {
            frame.getOperandStack().popObject();
        }
    }
}

class Pop2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        if (frame.getOperandStack().getTop() instanceof Long ||
                frame.getOperandStack().getTop() instanceof Double) {
            frame.getOperandStack().popObject();
        } else {
            frame.getOperandStack().popObject();
            frame.getOperandStack().popObject();
        }
    }
}
