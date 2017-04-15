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
class Frame {
    Frame lower;
    LocalVars localVars;
    OperandStack operandStack;
    
    Frame(int maxLocals, int maxStack) {
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
}
