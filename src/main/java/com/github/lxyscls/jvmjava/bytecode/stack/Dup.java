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
            Object o = stack.popRef();
            stack.pushRef(o);
            stack.pushRef(o);
        }
    }
}

class DupX1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o1 = stack.popRef();
            Object o2 = stack.popRef();
            stack.pushRef(o1);
            stack.pushRef(o2);
            stack.pushRef(o1);
        }
    }
}

class DupX2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();        
        Object o1 = stack.popRef();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o2 = stack.popRef();
            Object o3 = stack.popRef();
            stack.pushRef(o1);
            stack.pushRef(o3);
            stack.pushRef(o2);
            stack.pushRef(o1);
        } else {
            Object o2 = stack.popRef();
            stack.pushRef(o1);
            stack.pushRef(o2);
            stack.pushRef(o1);            
        }
    }
}
