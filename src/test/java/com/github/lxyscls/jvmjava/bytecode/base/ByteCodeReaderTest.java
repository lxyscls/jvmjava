/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class ByteCodeReaderTest {
    
    public ByteCodeReaderTest() {
    }

    @Test
    public void testReadUint8() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0xFF}, 0);
        short expResult = 255;
        short result = instance.readUint8();
        assertEquals(expResult, result);
    }

    @Test
    public void testReadInt8() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0xFF}, 0);
        byte expResult = -1;
        byte result = instance.readInt8();
        assertEquals(expResult, result);
    }

    @Test
    public void testReadUint16() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0xFF, (byte)0xFF}, 0);
        int expResult = 65535;
        int result = instance.readUint16();
        assertEquals(expResult, result);
    }

    @Test
    public void testReadInt16() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0xFF, (byte)0xFF}, 0);       
        short expResult = -1;
        short result = instance.readInt16();
        assertEquals(expResult, result);
    }

    @Test
    public void testReadInt32() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF}, 0);
        int expResult = -1;
        int result = instance.readInt32();
        assertEquals(expResult, result);
    }

    @Test
    public void testReadInt32s() {
        ByteCodeReader instance = new ByteCodeReader();
        instance.reset(new byte[] {(byte)0x2, 
            (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78,
            (byte)0x78, (byte)0x56, (byte)0x34, (byte)0x12}, 0);        
        int[] expResult = new int[] {305419896, 2018915346};
        int[] result = instance.readInt32s(instance.readUint8());
        assertArrayEquals(expResult, result);
    }

}
