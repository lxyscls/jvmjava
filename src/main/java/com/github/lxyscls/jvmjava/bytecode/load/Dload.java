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
public class Dload extends Index8ByteCode implements WideByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(index));
    }
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
}

class Dload0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(0));
    }
}

class Dload1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(1));
    }
}

class Dload2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(2));
    }
}

class Dload3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(3));
    }
}
