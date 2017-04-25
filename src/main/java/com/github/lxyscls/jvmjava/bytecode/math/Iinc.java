/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.math;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.WideByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.LocalVars;

/**
 *
 * @author sk-xinyilong
 */
public class Iinc extends ByteCode implements WideByteCode {
    private int index;
    private int cconst;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUint8();
        cconst = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars lv = frame.getLocalVars();
        lv.setInt(index, lv.getInt(index) + cconst);
    }

    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readUint16();
        cconst = reader.readInt16();
    }
    
    @Override
    public String toString() {
        return "index " + String.valueOf(index) + " const " + String.valueOf(cconst);
    }
}
