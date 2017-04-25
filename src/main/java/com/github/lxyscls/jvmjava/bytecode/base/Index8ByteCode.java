/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

/**
 *
 * @author sk-xinyilong
 */
public abstract class Index8ByteCode extends ByteCode {
    protected int index;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUint8();
    }
<<<<<<< HEAD

    @Override
    public String toString() {
        return "index: " + String.valueOf(index);
    }    
=======
>>>>>>> ceab5eeee8fec15ee2a35d22c56bbca886fdcd89
}
