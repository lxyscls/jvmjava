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
public class Fload extends Index8ByteCode implements WideByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(index));
    }
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
}

class Fload0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(0));
    }
}

class Fload1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(1));
    }
}

class Fload2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(2));
    }
}

class Fload3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(3));
    }
}
