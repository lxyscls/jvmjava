/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import java.util.LinkedList;
import java.util.Deque;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
public class OperandStack {
    private final Deque<Object> stack;

    public OperandStack(int maxStack) {
        stack = new LinkedList<>();
    }
    
    public void pushInt(Integer val) {
        stack.addFirst(val);
    }
    
    public int popInt() {
       return (Integer)stack.pollFirst();
    }
    
    public void pushFloat(Float val) {
        stack.addFirst(val);
    }
    
    public float popFloat() {
       return (Float)stack.pollFirst();
    }
    
    public void pushLong(Long val) {
        stack.addFirst(val);
    }
    
    public long popLong() {
       return (Long)stack.pollFirst();
    }
    
    public void pushDouble(Double val) {
        stack.addFirst(val);
    }
    
    public double popDouble() {
       return (Double)stack.pollFirst();
    }
    
    public void pushRef(Jobject ref) {
        stack.addFirst(ref);
    }
    
    public Jobject popRef() {
       return (Jobject)stack.pollFirst();
    }
    
    public void pushObject(Object obj) {
        stack.addFirst(obj);
    }
    
    public Object popObject() {
        return stack.pollFirst();
    }
    
    public Object getTop() {
        return stack.peekFirst();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        stack.forEach((slot) -> {
            sb.append(" {").append(String.valueOf(slot)).append("} ");
        });
        return sb.toString();
    }
}
