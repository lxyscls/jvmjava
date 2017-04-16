/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author sk-xinyilong
 */
class OperandStack {
    private final Deque<Object> stack;

    OperandStack(int maxStack) {
        stack = new ArrayDeque<>();
    }
    
    void pushInt(Integer val) {
        stack.addFirst(val);
    }
    
    int popInt() {
        return (Integer)stack.pollFirst();
    }
    
    void pushFloat(Float val) {
        stack.addFirst(val);
    }
    
    float popFloat() {
        return (Float)stack.pollFirst();
    }
    
    void pushLong(Long val) {
        stack.addFirst(val);
    }
    
    long popLong() {
        return (Long)stack.pollFirst();
    }
    
    void pushDouble(Double val) {
        stack.addFirst(val);
    }
    
    double popDouble() {
        return (Double)stack.pollFirst();
    }
    
    void pushRef(Object ref) {
        stack.addFirst(ref);
    }
    
    Object popRef() {
        return stack.pollFirst();
    }
}
