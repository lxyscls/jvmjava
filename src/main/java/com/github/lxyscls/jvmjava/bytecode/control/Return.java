/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.control;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
class Return extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}

class Ireturn extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().currentFrame();
        
        invokeFrame.getOperandStack().pushInt(lastFrame.getOperandStack().popInt());
    }
}

class Lreturn extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().currentFrame();
        
        invokeFrame.getOperandStack().pushLong(lastFrame.getOperandStack().popLong());
    }
}

class Freturn extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().currentFrame();
        
        invokeFrame.getOperandStack().pushFloat(lastFrame.getOperandStack().popFloat());
    }
}

class Dreturn extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().currentFrame();
        
        invokeFrame.getOperandStack().pushDouble(lastFrame.getOperandStack().popDouble());
    }
}

class Areturn extends NoOperandByteCode {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().currentFrame();
        
        invokeFrame.getOperandStack().pushRef(lastFrame.getOperandStack().popRef());
    }
}