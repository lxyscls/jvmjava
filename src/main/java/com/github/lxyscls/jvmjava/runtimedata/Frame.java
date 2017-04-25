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
public class Frame {
    Frame lower;
    private final LocalVars localVars;
    private final OperandStack operandStack;
<<<<<<< HEAD
    private final Jthread thread;
    private int nextPc;
    
    public Frame(Jthread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.nextPc = 0;
=======
    
    public Frame(int maxLocals, int maxStack) {
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
    
    public OperandStack getOperandStack() {
        return operandStack;
    }
    
    public LocalVars getLocalVars() {
        return localVars;
    }
<<<<<<< HEAD
    
    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
    
    public int getNextPc() {
        return this.nextPc;
    }
    
    public Jthread getThread() {
        return this.thread;
    }
=======
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
}
