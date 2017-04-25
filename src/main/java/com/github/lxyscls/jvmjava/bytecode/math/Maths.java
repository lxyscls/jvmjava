/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.math;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Maths {
    static Iadd IADD = new Iadd();
    static Ladd LADD = new Ladd();
    static Fadd FADD = new Fadd();
    static Dadd DADD = new Dadd();
    static Isub ISUB = new Isub();
    static Lsub LSUB = new Lsub();
    static Fsub FSUB = new Fsub();
    static Dsub DSUB = new Dsub();
    static Imul IMUL = new Imul();
    static Lmul LMUL = new Lmul();
    static Fmul FMUL = new Fmul();
    static Dmul DMUL = new Dmul();
    static Idiv IDIV = new Idiv();
    static Ldiv LDIV = new Ldiv();
    static Fdiv FDIV = new Fdiv();
    static Ddiv DDIV = new Ddiv();
    static Irem IREM = new Irem();
    static Lrem LREM = new Lrem();
    static Frem FREM = new Frem();
    static Drem DREM = new Drem();
    static Ineg INEG = new Ineg();
    static Lneg LNEG = new Lneg();
    static Fneg FNEG = new Fneg();
    static Dneg DNEG = new Dneg();
    static Ishl ISHL = new Ishl();
    static Lshl LSHL = new Lshl();
    static Ishr ISHR = new Ishr();
    static Lshr LSHR = new Lshr();
    static Iushr IUSHR = new Iushr();
    static Lushr LUSHR = new Lushr();
    static Iand IAND = new Iand();
    static Land LAND = new Land();
    static Ior IOR = new Ior();
    static Lor LOR = new Lor();
    static Ixor IXOR = new Ixor();
    static Lxor LXOR = new Lxor();

    public static ByteCode newMath(short opCode) {
        switch (opCode) {
            case 0x60: return IADD;
            case 0x61: return LADD;
            case 0x62: return FADD;
            case 0x63: return DADD;
            case 0x64: return ISUB;
            case 0x65: return LSUB;
            case 0x66: return FSUB;
            case 0x67: return DSUB;
            case 0x68: return IMUL;
            case 0x69: return LMUL;
            case 0x6a: return FMUL;
            case 0x6b: return DMUL;
            case 0x6c: return IDIV;
            case 0x6d: return LDIV;
            case 0x6e: return FDIV;
            case 0x6f: return DDIV;
            case 0x70: return IREM;
            case 0x71: return LREM;
            case 0x72: return FREM;
            case 0x73: return DREM;             
            case 0x74: return INEG;
            case 0x75: return LNEG;
            case 0x76: return FNEG;
            case 0x77: return DNEG;
            case 0x78: return ISHL;
            case 0x79: return LSHL;
            case 0x7a: return ISHR;
            case 0x7b: return LSHR;
            case 0x7c: return IUSHR;
            case 0x7d: return LUSHR;
            case 0x7e: return IAND;
            case 0x7f: return LAND;
            case 0x80: return IOR;
            case 0x81: return LOR;
            case 0x82: return IXOR;
            case 0x83: return LXOR;
            case 0x84: return new Iinc();
        }
        return null;
    }
}
