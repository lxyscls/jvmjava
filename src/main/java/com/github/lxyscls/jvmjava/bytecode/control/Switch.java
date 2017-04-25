/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.control;

import com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;

/**
 *
 * @author sk-xinyilong
 */
class TableSwitch extends ByteCode {
    private int low;
    private int high;
    private int defaultOffset;
    private int[] jumpOffsets;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        jumpOffsets = reader.readInt32s(high - low + 1);
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        if (index >= low && index <= high) {
            BranchByteCode.branch(frame, jumpOffsets[index - low]);
        } else {
            BranchByteCode.branch(frame, defaultOffset);
        }
    }
}

class LookupSwitch extends ByteCode {
    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int key = stack.popInt();
        for (int i = 0; i < matchOffsets.length; i += 2) {
            if (matchOffsets[i] == key) {
                BranchByteCode.branch(frame, matchOffsets[i+1]);
                return;
            }
        }
        BranchByteCode.branch(frame, defaultOffset);
    }
}