/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

<<<<<<< HEAD
import com.github.lxyscls.jvmjava.runtimedata.Frame;

=======
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
/**
 *
 * @author sk-xinyilong
 */
public abstract class BranchByteCode extends ByteCode {
<<<<<<< HEAD
    protected int offset;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        offset = reader.readInt16();
    }
    
    public static void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPc();
        frame.setNextPc(pc + offset);
    }
    
    @Override
    public String toString() {
        return "offset: " + String.valueOf(offset);
=======
    private int offset;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        offset = reader.readUint16();
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
    }
}
