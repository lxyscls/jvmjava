/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.constant;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */

class Bipush extends ByteCode {
    byte val;
    
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt((int)val);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        val = reader.readInt8();
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

class Sipush extends ByteCode {
    short val;
    
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt((int)val);
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        val = reader.readInt16();
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
