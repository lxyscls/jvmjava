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
class Dstore extends Index8ByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(index, frame.getOperandStack().popDouble());
    }
}

class Dstore0 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(0, frame.getOperandStack().popDouble());
    }
}

class Dstore1 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(1, frame.getOperandStack().popDouble());
    }
}

class Dstore2 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(2, frame.getOperandStack().popDouble());
    }
}

class Dstore3 extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(3, frame.getOperandStack().popDouble());
    }
}
