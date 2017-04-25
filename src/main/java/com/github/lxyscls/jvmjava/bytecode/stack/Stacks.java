/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.stack;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Stacks {
    static Pop POP = new Pop();
    static Pop2 POP2 = new Pop2();
    static Dup DUP = new Dup();
    static DupX1 DUPX1 = new DupX1();
    static DupX2 DUPX2 = new DupX2();
    static Dup2 DUP2 = new Dup2();
    static Dup2X1 DUP2X1 = new Dup2X1();
    static Dup2X2 DUP2X2 = new Dup2X2();
    static Swap SWAP = new Swap();
    
    public static ByteCode newStack(short opCode) {
        switch (opCode) {
            case 0x57: return POP;
            case 0x58: return POP2;
            case 0x59: return DUP;
            case 0x5a: return DUPX1;
            case 0x5b: return DUPX2;
            case 0x5c: return DUP2;
            case 0x5d: return DUP2X1;
            case 0x5e: return DUP2X2;
            case 0x5f: return SWAP;
        }
        return null;
    }
}
