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
class Fstore extends Index8ByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(index, frame.getOperandStack().popFloat());
    }
}

class Fstore0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(0, frame.getOperandStack().popFloat());
    }
}

class Fstore1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(1, frame.getOperandStack().popFloat());
    }
}

class Fstore2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(2, frame.getOperandStack().popFloat());
    }
}

class Fstore3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(3, frame.getOperandStack().popFloat());
    }
}
