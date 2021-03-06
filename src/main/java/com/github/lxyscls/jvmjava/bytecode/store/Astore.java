/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.store;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.WideByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class Astore extends Index8ByteCode implements WideByteCode { 
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(index, frame.getOperandStack().popRef());
    }
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
}

class Astore0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(0, frame.getOperandStack().popRef());
    }
}

class Astore1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(1, frame.getOperandStack().popRef());
    }
}

class Astore2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(2, frame.getOperandStack().popRef());
    }
}

class Astore3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(3, frame.getOperandStack().popRef());
    }
}
