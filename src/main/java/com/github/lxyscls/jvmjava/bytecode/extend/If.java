/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.extend;

import com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode;
import static com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode.branch;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class IfNull extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        if (ref == null) {
            branch(frame, offset);
        }
    }
}

class IfNonNull extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        if (ref != null) {
            branch(frame, offset);
        }
    }
}
