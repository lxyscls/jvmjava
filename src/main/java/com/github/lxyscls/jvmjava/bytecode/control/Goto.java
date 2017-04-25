/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.control;

import com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
class Goto extends BranchByteCode {
    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
