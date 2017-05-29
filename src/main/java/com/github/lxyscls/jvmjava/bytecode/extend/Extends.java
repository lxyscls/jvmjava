/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.extend;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;

/**
 *
 * @author sk-xinyilong
 */
public class Extends {
    public static ByteCode newExtend(short opCode) {
        switch (opCode) {
            case 0xc4: return new Wide();
            case 0xc5: return new MultiANewArray();
            case 0xc6: return new IfNull();
            case 0xc7: return new IfNonNull();
            case 0xc8: return new GotoW();
        }
        return null;
    }
}
