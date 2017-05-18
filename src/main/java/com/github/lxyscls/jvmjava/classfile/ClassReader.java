/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author sk-xinyilong
 */
public class ClassReader {
    private final ByteArrayInputStream is;
    
    public ClassReader(byte[] ba) {
        is = new ByteArrayInputStream(ba);
    }
    
    public short readUint8() {
        byte[] ba = new byte[2];
        is.read(ba, 1, 1);
        return new BigInteger(ba).shortValue();
    }
    
    public int readUint16() {
        byte[] ba = new byte[4];
        is.read(ba, 2, 2);
        return new BigInteger(ba).intValue();
    }
    
    public long readUint32() {
        byte[] ba = new byte[8];
        is.read(ba, 4, 4);
        return new BigInteger(ba).longValue();
    }
    
    public BigInteger readUint64() {
        byte[] ba = new byte[8];
        is.read(ba, 0, 8);
        return new BigInteger(ba);
    }
    
    public int[] readUint16s() {
        int[] s = new int[readUint16()];
        for (int i = 0; i < s.length; i++) {
            s[i] = readUint16();
        }
        return s;
    }
    
    public byte[] readBytes(int length) {
        byte[] ba = new byte[length];
        is.read(ba, 0, length);
        return ba;
    }
    
    public String readUtf8() throws IOException {
        return new DataInputStream(is).readUTF();
    }
}
