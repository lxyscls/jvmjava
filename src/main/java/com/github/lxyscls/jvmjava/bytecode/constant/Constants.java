/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.constant;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Constants {
    static Nop NOP = new Nop();
    static AconstNull ACONSTNULL = new AconstNull();
    static IconstM1 ICONSTM1 = new IconstM1();
    static Iconst0 ICONST0 = new Iconst0();
    static Iconst1 ICONST1 = new Iconst1();
    static Iconst2 ICONST2 = new Iconst2();
    static Iconst3 ICONST3 = new Iconst3();
    static Iconst4 ICONST4 = new Iconst4();
    static Iconst5 ICONST5 = new Iconst5();
    static Lconst0 LCONST0 = new Lconst0();
    static Lconst1 LCONST1 = new Lconst1();
    static Fconst0 FCONST0 = new Fconst0();
    static Fconst1 FCONST1 = new Fconst1();
    static Fconst2 FCONST2 = new Fconst2();
    static Dconst0 DCONST0 = new Dconst0();
    static Dconst1 DCONST1 = new Dconst1();
    
    public static ByteCode newConstant(short opCode) {    
        switch (opCode) {
            case 0x00: return NOP;
            case 0x01: return ACONSTNULL;
            case 0x02: return ICONSTM1;
            case 0x03: return ICONST0;
            case 0x04: return ICONST1;
            case 0x05: return ICONST2;
            case 0x06: return ICONST3;
            case 0x07: return ICONST4;
            case 0x08: return ICONST5;
            case 0x09: return LCONST0;
            case 0x0a: return LCONST1;
            case 0x0b: return FCONST0;
            case 0x0c: return FCONST1;
            case 0x0d: return FCONST2;
            case 0x0e: return DCONST0;
            case 0x0f: return DCONST1;
            case 0x10: return new Bipush();
            case 0x11: return new Sipush();
            case 0x12: return new Ldc();
            case 0x13: return new Ldc_w();
            case 0x14: return new Ldc2_w();
        }
        return null;
    }
}
