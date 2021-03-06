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
public abstract class Index16ByteCode extends ByteCode {
    protected int index;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUint16();
    }
    
    @Override
    public String toString() {
        return "index: " + String.valueOf(index);
    }
}
