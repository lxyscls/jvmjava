/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.stack;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class Dup extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o = stack.popObject();
            stack.pushObject(o);
            stack.pushObject(o);
        }
    }
}

class DupX1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o1 = stack.popObject();
            Object o2 = stack.popObject();
            stack.pushObject(o1);
            stack.pushObject(o2);
            stack.pushObject(o1);
        }
    }
}

class DupX2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();        
        Object o1 = stack.popObject();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o2 = stack.popObject();
            Object o3 = stack.popObject();
            stack.pushObject(o1);
            stack.pushObject(o3);
            stack.pushObject(o2);
            stack.pushObject(o1);
        } else {
            Object o2 = stack.popObject();
            stack.pushObject(o1);
            stack.pushObject(o2);
            stack.pushObject(o1);            
        }
    }
}
