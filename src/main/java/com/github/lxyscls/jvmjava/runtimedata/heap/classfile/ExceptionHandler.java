/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;

/**
 *
 * @author sk-xinyilong
 */
public class ExceptionHandler {
    private final int startPc;
    private final int endPc;
    private final int handlerPc;
    private final ClassRef catchType;
    
    public ExceptionHandler(int startPc, int endPc, int handlerPc, ClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }
    
    public int getStartPc() {
        return startPc;
    }
    
    public int getEndPc() {
        return endPc;
    }
    
    public int getHanlderPc() {
        return handlerPc;
    }
    
    public ClassRef getCatchType() {
        return catchType;
    }
}
