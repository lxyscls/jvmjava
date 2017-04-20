/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.store;

import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
class Lstore extends Index8ByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(index, frame.getOperandStack().popLong());
    }
}

class Lstore0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(0, frame.getOperandStack().popLong());
    }
}

class Lstore1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(1, frame.getOperandStack().popLong());
    }
}

class Lstore2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(2, frame.getOperandStack().popLong());
    }
}

class Lstore3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(3, frame.getOperandStack().popLong());
    }
}
