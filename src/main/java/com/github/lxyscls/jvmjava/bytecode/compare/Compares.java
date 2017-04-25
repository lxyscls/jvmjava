/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.compare;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Compares {
    static Lcmp LCMP = new Lcmp();
    static Fcmpl FCMPL = new Fcmpl();
    static Fcmpg FCMPG = new Fcmpg();
    static Dcmpl DCMPL = new Dcmpl();
    static Dcmpg DCMPG = new Dcmpg();
    
    public static ByteCode newCompare(short opCode) {
        switch (opCode) {
            case 0x94: return LCMP;
            case 0x95: return FCMPL;
            case 0x96: return FCMPG;
            case 0x97: return DCMPL;
            case 0x98: return DCMPG;
            case 0x99: return new Ifeq();
            case 0x9a: return new Ifne();
            case 0x9b: return new Iflt();
            case 0x9c: return new Ifge();
            case 0x9d: return new Ifgt();
            case 0x9e: return new Ifle();
            case 0x9f: return new IfIcmpeq();
            case 0xa0: return new IfIcmpne();
            case 0xa1: return new IfIcmplt();
            case 0xa2: return new IfIcmpge();
            case 0xa3: return new IfIcmpgt();
            case 0xa4: return new IfIcmple();
            case 0xa5: return new IfAcmpeq();
            case 0xa6: return new IfAcmpne();
        }
        return null;
    }
}
