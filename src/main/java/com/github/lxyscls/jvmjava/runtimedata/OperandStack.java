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
public class OperandStack {
    private final Object[] slots;
    private int top = 0;

    public OperandStack(int maxStack) {
        slots = new Object[maxStack];
    }
    
    public void pushInt(Integer val) {
        slots[top++] = val;
<<<<<<< HEAD
    }
    
    public int popInt() {
       int ret = (Integer)slots[--top]; 
       slots[top] = null;
       return ret;
    }
    
    public void pushFloat(Float val) {
        slots[top++] = val;
    }
    
    public float popFloat() {
        float ret = (Float)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushLong(Long val) {
        slots[top++] = val;
    }
    
    public long popLong() {
        long ret = (Long)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushDouble(Double val) {
        slots[top++] = val;
    }
    
    public double popDouble() {
        double ret = (Double)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushRef(Object ref) {
        slots[top++] = ref;
    }
    
    public Object popRef() {
        Object ref = slots[--top];
        slots[top] = null;
        return ref;
    }
    
    public Object getTop() {
        return slots[top-1];
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object slot : slots) {
            sb.append(" {").append(String.valueOf(slot)).append("} ");
        }
        return sb.toString();
=======
    }
    
    public int popInt() {
       int ret = (Integer)slots[--top]; 
       slots[top] = null;
       return ret;
    }
    
    public void pushFloat(Float val) {
        slots[top++] = val;
    }
    
    public float popFloat() {
        float ret = (Float)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushLong(Long val) {
        slots[top++] = val;
    }
    
    public long popLong() {
        long ret = (Long)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushDouble(Double val) {
        slots[top++] = val;
    }
    
    public double popDouble() {
        double ret = (Double)slots[--top];
        slots[top] = null;
        return ret;
    }
    
    public void pushRef(Object ref) {
        slots[top++] = ref;
    }
    
    public Object popRef() {
        Object ref = slots[--top];
        slots[top] = null;
        return ref;
    }
    
    public Object getTop() {
        return slots[top-1];
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
    }
}
