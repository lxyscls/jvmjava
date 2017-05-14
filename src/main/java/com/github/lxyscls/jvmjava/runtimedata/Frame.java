/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;

/**
 *
 * @author sk-xinyilong
 */
public class Frame {
    Frame lower;
    private final LocalVars localVars;
    private final OperandStack operandStack;
    private final Method method;
    private final Jthread thread;
    private int nextPc;
    
    public Frame(Jthread thread, Method method) {
        this.thread = thread;
        this.nextPc = 0;
        this.method = method;
        localVars = new LocalVars(this.method.getMaxLocals());
        operandStack = new OperandStack(this.method.getMaxStack());
    }
    
    public OperandStack getOperandStack() {
        return operandStack;
    }
    
    public LocalVars getLocalVars() {
        return localVars;
    }
    
    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
    
    public int getNextPc() {
        return this.nextPc;
    }
    
    public Jthread getThread() {
        return this.thread;
    }
    
    public Method getMethod() {
        return this.method;
    }
}
