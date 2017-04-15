/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

/**
 *
 * @author sk-xinyilong
 */
class OperandStack {
    private final Object[] slots;
    private int top = 0;

    OperandStack(int maxStack) {
        slots = new Object[maxStack];
    }
    
    void pushInt(Integer val) {
        slots[top++] = val;
    }
    
    int popInt() {
       int ret = (Integer)slots[--top]; 
       slots[top] = null;
       return ret;
    }
    
    void pushFloat(Float val) {
        slots[top++] = val;
    }
    
    float popFloat() {
        float ret = (Float)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    void pushLong(Long val) {
        slots[top++] = val;
    }
    
    long popLong() {
        long ret = (Long)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    void pushDouble(Double val) {
        slots[top++] = val;
    }
    
    double popDouble() {
        double ret = (Double)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    void pushRef(Object ref) {
        slots[top++] = ref;
    }
    
    Object popRef() {
        Object ref = slots[--top];
        slots[top] = null;
        return ref;
    }
}
