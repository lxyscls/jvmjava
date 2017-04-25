/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.extend;

import com.github.lxyscls.jvmjava.bytecode.base.BranchByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.WideByteCode;
import com.github.lxyscls.jvmjava.bytecode.load.Aload;
import com.github.lxyscls.jvmjava.bytecode.load.Dload;
import com.github.lxyscls.jvmjava.bytecode.load.Fload;
import com.github.lxyscls.jvmjava.bytecode.load.Iload;
import com.github.lxyscls.jvmjava.bytecode.load.Lload;
import com.github.lxyscls.jvmjava.bytecode.math.Iinc;
import com.github.lxyscls.jvmjava.bytecode.store.Astore;
import com.github.lxyscls.jvmjava.bytecode.store.Dstore;
import com.github.lxyscls.jvmjava.bytecode.store.Fstore;
import com.github.lxyscls.jvmjava.bytecode.store.Istore;
import com.github.lxyscls.jvmjava.bytecode.store.Lstore;
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
class Wide extends ByteCode {
    private WideByteCode wideByteCode;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        short opCode = reader.readUint8();
        switch (opCode) {
            case 0x15:
                wideByteCode = new Iload(); 
                wideByteCode.fetchOperandsW(reader);
                break;
            case 0x16:
                wideByteCode = new Lload(); 
                wideByteCode.fetchOperandsW(reader);
                break;
            case 0x17:
                wideByteCode = new Fload(); 
                wideByteCode.fetchOperandsW(reader);                
                break;
            case 0x18:
                wideByteCode = new Dload(); 
                wideByteCode.fetchOperandsW(reader);                
                break;
            case 0x19:
                wideByteCode = new Aload(); 
                wideByteCode.fetchOperandsW(reader);                
                break;
            case 0x36:
                wideByteCode = new Istore(); 
                wideByteCode.fetchOperandsW(reader);                
                break;
            case 0x37:
                wideByteCode = new Lstore(); 
                wideByteCode.fetchOperandsW(reader);                
                break;                
            case 0x38:
                wideByteCode = new Fstore();
                wideByteCode.fetchOperandsW(reader);                
                break;                
            case 0x39:
                wideByteCode = new Dstore(); 
                wideByteCode.fetchOperandsW(reader);                
                break;                
            case 0x3a:
                wideByteCode = new Astore(); 
                wideByteCode.fetchOperandsW(reader);                
                break;                
            case 0x84:
                wideByteCode = new Iinc();
                wideByteCode.fetchOperandsW(reader);
                break;
            case 0xa9:
                throw new UnsupportedOperationException("!!!ret!!!");
        }
    }

    @Override
    public void execute(Frame frame) {
        ByteCode bc = (ByteCode)wideByteCode;
        bc.execute(frame);
    }
}

class GotoW extends BranchByteCode {
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
