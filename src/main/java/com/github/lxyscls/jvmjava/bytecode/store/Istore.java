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
public class Istore extends Index8ByteCode implements WideByteCode {       
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(index, frame.getOperandStack().popInt());
    }
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
}

class Istore0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(0, frame.getOperandStack().popInt());
    }
}

class Istore1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(1, frame.getOperandStack().popInt());
    }
}

class Istore2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(2, frame.getOperandStack().popInt());
    }
}

class Istore3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(3, frame.getOperandStack().popInt());
    }
}
