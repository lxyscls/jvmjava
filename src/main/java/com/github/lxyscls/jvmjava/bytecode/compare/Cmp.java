/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.compare;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class Lcmp extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long value2 = stack.popLong();
        long value1 = stack.popLong();
        
        if (value1 > value2) {
            stack.pushInt(1);
        } else if (value1 < value2) {
            stack.pushInt(-1);
        } else {
            stack.pushInt(0);
        }
    }
}

class Fcmpl extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float value2 = stack.popFloat();
        float value1 = stack.popFloat();
        
        if (Float.isNaN(value1) || Float.isNaN(value2)) {
            stack.pushInt(-1);
        } else {
            stack.pushInt(Float.compare(value1, value2));
        }
    }
}

class Fcmpg extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float value2 = stack.popFloat();
        float value1 = stack.popFloat();
        
        if (Float.isNaN(value1) || Float.isNaN(value2)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(Float.compare(value1, value2));
        }
    }
}

class Dcmpl extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double value2 = stack.popDouble();
        double value1 = stack.popDouble();
        
        if (Double.isNaN(value1) || Double.isNaN(value2)) {
            stack.pushInt(-1);
        } else {
            stack.pushInt(Double.compare(value1, value2));
        }
    }
}

class Dcmpg extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double value2 = stack.popDouble();
        double value1 = stack.popDouble();
        
        if (Double.isNaN(value1) || Double.isNaN(value2)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(Double.compare(value1, value2));
        }
    }
}