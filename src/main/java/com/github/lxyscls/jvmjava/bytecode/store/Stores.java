/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.store;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Stores {
    static Istore0 ISTORE0 = new Istore0();
    static Istore1 ISTORE1 = new Istore1();
    static Istore2 ISTORE2 = new Istore2();
    static Istore3 ISTORE3 = new Istore3();
    static Lstore0 LSTORE0 = new Lstore0();
    static Lstore1 LSTORE1 = new Lstore1();
    static Lstore2 LSTORE2 = new Lstore2();
    static Lstore3 LSTORE3 = new Lstore3();  
    static Fstore0 FSTORE0 = new Fstore0();
    static Fstore1 FSTORE1 = new Fstore1();
    static Fstore2 FSTORE2 = new Fstore2();
    static Fstore3 FSTORE3 = new Fstore3();
    static Dstore0 DSTORE0 = new Dstore0();
    static Dstore1 DSTORE1 = new Dstore1();
    static Dstore2 DSTORE2 = new Dstore2();
    static Dstore3 DSTORE3 = new Dstore3();    
    static Astore0 ASTORE0 = new Astore0();
    static Astore1 ASTORE1 = new Astore1();
    static Astore2 ASTORE2 = new Astore2();
    static Astore3 ASTORE3 = new Astore3();     
  
    public static ByteCode newStore(short opCode) {
        switch (opCode) {
            case 0x36: return new Istore();
            case 0x37: return new Lstore();
            case 0x38: return new Fstore();
            case 0x39: return new Dstore();
            case 0x3a: return new Astore();
            case 0x3b: return ISTORE0;
            case 0x3c: return ISTORE1;
            case 0x3d: return ISTORE2;
            case 0x3e: return ISTORE3;
            case 0x3f: return LSTORE0;
            case 0x40: return LSTORE1;
            case 0x41: return LSTORE2;
            case 0x42: return LSTORE3;
            case 0x43: return FSTORE0;
            case 0x44: return FSTORE1;
            case 0x45: return FSTORE2;
            case 0x46: return FSTORE3;
            case 0x47: return DSTORE0;
            case 0x48: return DSTORE1;
            case 0x49: return DSTORE2;
            case 0x4a: return DSTORE3;
            case 0x4b: return ASTORE0;
            case 0x4c: return ASTORE1;
            case 0x4d: return ASTORE2;
            case 0x4e: return ASTORE3;
        }
        return null;
    }    
}
