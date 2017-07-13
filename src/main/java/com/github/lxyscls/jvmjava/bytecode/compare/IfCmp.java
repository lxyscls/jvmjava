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
class IfIcmpeq extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 == value2) {
            branch(frame, offset);
        }
    } 
}

class IfIcmpne extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 != value2) {
            branch(frame, offset);
        }
    } 
}

class IfIcmplt extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 < value2) {
            branch(frame, offset);
        }
    } 
}

class IfIcmple extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 <= value2) {
            branch(frame, offset);
        }
    } 
}

class IfIcmpgt extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 > value2) {
            branch(frame, offset);
        }
    } 
}

class IfIcmpge extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        
        if (value1 >= value2) {
            branch(frame, offset);
        }
    } 
}

class IfAcmpeq extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object value2 = stack.popRef();
        Object value1 = stack.popRef();
        
        if (value1 == value2) {
            branch(frame, offset);
        }
    }
}

class IfAcmpne extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object value2 = stack.popRef();
        Object value1 = stack.popRef();
        
        if (value1 != value2) {
            branch(frame, offset);
        }
    }
}