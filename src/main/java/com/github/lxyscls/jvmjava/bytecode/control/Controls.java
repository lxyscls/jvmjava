/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.control;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Controls {
    static Return RET = new Return();
    static Ireturn IRET = new Ireturn();
    static Lreturn LRET = new Lreturn();
    static Freturn FRET = new Freturn();
    static Dreturn DRET = new Dreturn();
    static Areturn ARET = new Areturn();
    
    public static ByteCode newControl(short opCode) {
        switch (opCode) {
            case 0xa7: return new Goto();
            case 0xaa: return new TableSwitch();
            case 0xab: return new LookupSwitch();
            case 0xac: return IRET;
            case 0xad: return LRET;
            case 0xae: return FRET;
            case 0xaf: return DRET;
            case 0xb0: return ARET;
            case 0xb1: return RET;
        }
        return null;
    }
}
