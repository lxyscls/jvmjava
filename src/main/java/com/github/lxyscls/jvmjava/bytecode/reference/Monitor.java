/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
class MonitorEnter extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        Jobject obj = frame.getOperandStack().popRef();
        if (obj == null) {
            throw new NullPointerException();
        }
    }
    
}

class MonitorExit extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        Jobject obj = frame.getOperandStack().popRef();
        if (obj == null) {
            throw new NullPointerException();
        }
    }
    
}
