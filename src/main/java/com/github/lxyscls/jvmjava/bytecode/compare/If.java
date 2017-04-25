/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.compare;

import com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode;
import static com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode.branch;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class Ifeq extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() == 0) {
            branch(frame, offset);
        }
    }    
}

class Ifne extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() != 0) {
            branch(frame, offset);
        }
    }    
}

class Iflt extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() < 0) {
            branch(frame, offset);
        }
    }    
}

class Ifle extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() <= 0) {
            branch(frame, offset);
        }
    }    
}

class Ifgt extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() > 0) {
            branch(frame, offset);
        }
    }    
}

class Ifge extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        if (stack.popInt() >= 0) {
            branch(frame, offset);
        }
    }    
}