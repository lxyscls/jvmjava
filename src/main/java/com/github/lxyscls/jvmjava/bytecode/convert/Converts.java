/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.convert;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Converts {
    static I2l I2L = new I2l();
    static I2f I2F = new I2f();
    static I2d I2D = new I2d();
    static L2i L2I = new L2i();
    static L2f L2F = new L2f();
    static L2d L2D = new L2d();
    static F2i F2I = new F2i();
    static F2l F2L = new F2l();
    static F2d F2D = new F2d();
    static D2i D2I = new D2i();
    static D2l D2L = new D2l();
    static D2f D2F = new D2f();
    static I2b I2B = new I2b();
    static I2c I2C = new I2c();
    static I2s I2S = new I2s();    
    
    public static ByteCode newConvert(short opCode) {
        switch (opCode) {
            case 0x85: return I2L;
            case 0x86: return I2F;
            case 0x87: return I2D;
            case 0x88: return L2I;
            case 0x89: return L2F;
            case 0x8a: return L2D;
            case 0x8b: return F2I;
            case 0x8c: return F2L;
            case 0x8d: return F2D;
            case 0x8e: return D2I;
            case 0x8f: return D2L;
            case 0x90: return D2F;
            case 0x91: return I2B;
            case 0x92: return I2C;
            case 0x93: return I2S;
        }
        return null;
    }
}
