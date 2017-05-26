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
class Irem extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Integer value2 = stack.popInt();
        Integer value1 = stack.popInt();
        stack.pushInt(value1 % value2);
    }
}

class Lrem extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Long value2 = stack.popLong();
        Long value1 = stack.popLong();        
        stack.pushLong(value1 % value2);
    }    
}

class Frem extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Float value2 = stack.popFloat();
        Float value1 = stack.popFloat();         
        stack.pushFloat(value1 % value2);
    }    
}

class Drem extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Double value2 = stack.popDouble();
        Double value1 = stack.popDouble();          
        stack.pushDouble(value1 % value2);
    }    
}
