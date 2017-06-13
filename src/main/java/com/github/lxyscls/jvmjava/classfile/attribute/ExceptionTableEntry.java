/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.attribute;

/**
 *
 * @author sk-xinyilong
 */
public class ExceptionTableEntry {
    private final int startPc;
    private final int endPc;
    private final int handlePc;
    private final int catchType;
    
    ExceptionTableEntry(int startPc, int endPc, int handlePc, int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlePc = handlePc;
        this.catchType = catchType;
    }
    
    public int getStartPc() {
        return startPc;
    }
    
    public int getEndPc() {
        return endPc;
    }
    
    public int getHandlePc() {
        return handlePc;
    }
    
    public int getCatchType() {
        return catchType;
    }
}
