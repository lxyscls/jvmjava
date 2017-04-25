/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.store;

<<<<<<< HEAD
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.WideByteCode;
=======
import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
import com.github.lxyscls.jvmjava.runtimedata.Frame;

/**
 *
 * @author sk-xinyilong
 */
<<<<<<< HEAD
public class Dstore extends Index8ByteCode implements WideByteCode {       
=======
class Dstore extends Index8ByteCode {
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(index, frame.getOperandStack().popDouble());
    }
<<<<<<< HEAD
    
    @Override
    public void fetchOperandsW(ByteCodeReader reader) {
        index = reader.readInt16();
    }    
=======
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
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
