/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;

/**
 *
 * @author sk-xinyilong
 */
public class ByteCodeReader {
    private final ByteArrayInputStream is;
    private int pc;
    
    public ByteCodeReader(byte[] code, int pc) {
        this.is = new ByteArrayInputStream(code);
        this.pc = pc;
    }

    public int readUint16() {
        byte[] ba = new byte[4];
        is.read(ba, 2, 2);
        return new BigInteger(ba).intValue();
    }

    public short readUint8() {
        byte[] ba = new byte[2];
        is.read(ba, 1, 1);
        return new BigInteger(ba).shortValue();
    }
        
    public byte readInt8() {
        byte[] ba = new byte[1];
        is.read(ba, 0, 1);
        return new BigInteger(ba).byteValue();
    }
    
    public short readInt16() {
        byte[] ba = new byte[2];
        is.read(ba, 0, 2);
        return new BigInteger(ba).shortValue();
    }

    public int readInt32() {
        byte[] ba = new byte[4];
        is.read(ba, 0, 4);
        return new BigInteger(ba).intValue();
    }
}
