/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reserved;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Reserveds {
    static InvokeNative INATIVE = new InvokeNative();
    
    public static ByteCode newReserved(short opCode) {
        switch (opCode) {
            case 0xfe: return INATIVE;
        }
        return null;
    }
}
