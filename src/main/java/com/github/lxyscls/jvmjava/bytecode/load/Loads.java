/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.load;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Loads {
    static Iload0 ILOAD0 = new Iload0();
    static Iload1 ILOAD1 = new Iload1();
    static Iload2 ILOAD2 = new Iload2();
    static Iload3 ILOAD3 = new Iload3();
    static Lload0 LLOAD0 = new Lload0();
    static Lload1 LLOAD1 = new Lload1();
    static Lload2 LLOAD2 = new Lload2();
    static Lload3 LLOAD3 = new Lload3();  
    static Fload0 FLOAD0 = new Fload0();
    static Fload1 FLOAD1 = new Fload1();
    static Fload2 FLOAD2 = new Fload2();
    static Fload3 FLOAD3 = new Fload3();
    static Dload0 DLOAD0 = new Dload0();
    static Dload1 DLOAD1 = new Dload1();
    static Dload2 DLOAD2 = new Dload2();
    static Dload3 DLOAD3 = new Dload3();    
    static Aload0 ALOAD0 = new Aload0();
    static Aload1 ALOAD1 = new Aload1();
    static Aload2 ALOAD2 = new Aload2();
    static Aload3 ALOAD3 = new Aload3();
    static Iaload IALOAD = new Iaload();
    static Laload LALOAD = new Laload();
    static Faload FALOAD = new Faload();
    static Daload DALOAD = new Daload();
    static Aaload AALOAD = new Aaload();
    static Baload BALOAD = new Baload();
    static Caload CALOAD = new Caload();
    static Saload SALOAD = new Saload();    
  
    public static ByteCode newLoad(short opCode) {
        switch (opCode) {
            case 0x15: return new Iload();
            case 0x16: return new Lload();
            case 0x17: return new Fload();
            case 0x18: return new Dload();
            case 0x19: return new Aload();
            case 0x1a: return ILOAD0;
            case 0x1b: return ILOAD1;
            case 0x1c: return ILOAD2;
            case 0x1d: return ILOAD3;
            case 0x1e: return LLOAD0;
            case 0x1f: return LLOAD1;
            case 0x20: return LLOAD2;
            case 0x21: return LLOAD3;
            case 0x22: return FLOAD0;
            case 0x23: return FLOAD1;
            case 0x24: return FLOAD2;
            case 0x25: return FLOAD3;
            case 0x26: return DLOAD0;
            case 0x27: return DLOAD1;
            case 0x28: return DLOAD2;
            case 0x29: return DLOAD3;
            case 0x2a: return ALOAD0;
            case 0x2b: return ALOAD1;
            case 0x2c: return ALOAD2;
            case 0x2d: return ALOAD3;
            case 0x2e: return IALOAD;
            case 0x2f: return LALOAD;
            case 0x30: return FALOAD;
            case 0x31: return DALOAD;
            case 0x32: return AALOAD;
            case 0x33: return BALOAD;
            case 0x34: return CALOAD;
            case 0x35: return SALOAD;
        }
        return null;
    }
}
