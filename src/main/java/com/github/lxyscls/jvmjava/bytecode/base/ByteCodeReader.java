/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

import java.math.BigInteger;

/**
 *
 * @author sk-xinyilong
 */
public class ByteCodeReader {
    private byte[] code;
    private int pc;
    
    public ByteCodeReader() {
    }
    
    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public short readUint8() {
        byte[] ba = new byte[2];
        ba[1] = code[pc++];
        return new BigInteger(ba).shortValue();
    }
        
    public byte readInt8() {
        byte[] ba = new byte[1];
        ba[0] = code[pc++];
        return new BigInteger(ba).byteValue();
    }
    
    public int readUint16() {
        byte[] ba = new byte[4];
        ba[2] = code[pc];
        ba[3] = code[pc+1];
        pc += 2;
        return new BigInteger(ba).intValue();
    }
    
    public short readInt16() {
        byte[] ba = new byte[2];
        ba[0] = code[pc];
        ba[1] = code[pc+1];
        pc += 2;
        return new BigInteger(ba).shortValue();
    }

    public int readInt32() {
        byte[] ba = new byte[4];
        ba[0] = code[pc];
        ba[1] = code[pc+1];
        ba[2] = code[pc+2];
        ba[3] = code[pc+3];
        pc += 4;
        return new BigInteger(ba).intValue();
    }
    
    public int[] readInt32s(int length) {
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = readInt32();
        }
        return ints;
    }
    
    public void skipPadding() {
        while (pc % 4 != 0) {
            readInt8();
        }
    }
    
    public int getPc() {
        return pc;
    }
}
