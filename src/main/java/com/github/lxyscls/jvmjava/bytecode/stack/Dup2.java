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
class Dup2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o1 = stack.popRef();
            Object o2 = stack.popRef();
            stack.pushRef(o2);
            stack.pushRef(o1);
            stack.pushRef(o2);
            stack.pushRef(o1);
        } else {
            Object o = stack.popRef();
            stack.pushRef(o);
            stack.pushRef(o);
        }
    }
}

class Dup2X1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (!(stack.getTop() instanceof Long || 
                stack.getTop() instanceof Double)) {
            Object o1 = stack.popRef();
            Object o2 = stack.popRef();
            Object o3 = stack.popRef();
            stack.pushRef(o2);
            stack.pushRef(o1);
            stack.pushRef(o3);
            stack.pushRef(o2);
            stack.pushRef(o1);
        } else {
            Object o1 = stack.popRef();
            Object o2 = stack.popRef();
            stack.pushRef(o1);
            stack.pushRef(o2);
            stack.pushRef(o1);            
        }
    }
}

class Dup2X2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object o1 = stack.popRef();
        Object o2 = stack.popRef();
        if ((o1 instanceof Long || o1 instanceof Double) &&
                !(o2 instanceof Long || o2 instanceof Double) &&
                !(stack.getTop() instanceof Long ||
                stack.getTop() instanceof Double)) { // II
            Object o3 = stack.popRef();
            stack.pushRef(o1);
            stack.pushRef(o3);
            stack.pushRef(o2);
            stack.pushRef(o1);            
        } else if (!(o1 instanceof Long || o1 instanceof Double) &&
                !(o2 instanceof Long || o2 instanceof Double) &&
                (stack.getTop() instanceof Long ||
                stack.getTop() instanceof Double)) { // III
            Object o3 = stack.popRef();
            stack.pushRef(o2);
            stack.pushRef(o1);
            stack.pushRef(o3);
            stack.pushRef(o2);
            stack.pushRef(o1);            
        } else if ((o1 instanceof Long || o1 instanceof Double) &&
                (o2 instanceof Long || o2 instanceof Double)) { // IV
            stack.pushRef(o1);
            stack.pushRef(o2);
            stack.pushRef(o1);
        } else { // I
            Object o3 = stack.popRef();
            Object o4 = stack.popRef();
            stack.pushRef(o2);
            stack.pushRef(o1);
            stack.pushRef(o4);
            stack.pushRef(o3);
            stack.pushRef(o2);
            stack.pushRef(o1);            
        }
    }
}
