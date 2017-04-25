/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.load;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.WideByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
public class Iload extends Index8ByteCode implements WideByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(index));
    }
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
}

class Iload0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(0));
    }
}

class Iload1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(1));
    }
}

class Iload2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(2));
    }
}

class Iload3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(3));
    }
}