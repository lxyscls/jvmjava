/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.base;

import com.github.lxyscls.jvmjava.bytecode.compare.Compares;
import com.github.lxyscls.jvmjava.bytecode.constant.Constants;
import com.github.lxyscls.jvmjava.bytecode.control.Controls;
import com.github.lxyscls.jvmjava.bytecode.convert.Converts;
import com.github.lxyscls.jvmjava.bytecode.extend.Extends;
import com.github.lxyscls.jvmjava.bytecode.load.Loads;
import com.github.lxyscls.jvmjava.bytecode.math.Maths;
import com.github.lxyscls.jvmjava.bytecode.stack.Stacks;
import com.github.lxyscls.jvmjava.bytecode.store.Stores;

/**
 *
 * @author sk-xinyilong
 */
public class ByteCodes {
    public static ByteCode newByteCode(short opCode) {
        if (opCode >= 0x00 && opCode <= 0x14) {
            return Constants.newConstant(opCode);
        } else if (opCode >= 0x15 && opCode <= 0x35) {
            return Loads.newLoad(opCode);
        } else if (opCode >= 0x36 && opCode <= 0x56) {
            return Stores.newStore(opCode);
        } else if (opCode >= 0x57 && opCode <= 0x5f) {
            return Stacks.newStack(opCode);
        } else if (opCode >= 0x60 && opCode <= 0x84) {
            return Maths.newMath(opCode);
        } else if (opCode >= 0x85 && opCode <= 0x93) {
            return Converts.newConvert(opCode);
        } else if (opCode >= 0x94 && opCode <= 0xa6) {
            return Compares.newCompare(opCode);
        } else if (opCode >= 0xa7 && opCode <= 0xb1) {
            return Controls.newControl(opCode);
        } else if (opCode >= 0xb2 && opCode <= 0xc3) {
            throw new UnsupportedOperationException(String.valueOf(opCode)); // reference
        } else if (opCode >= 0xc4 && opCode <= 0xc9) {
            return Extends.newExtend(opCode);
        } else if (opCode >= 0xca && opCode <= 0xff) {
            throw new UnsupportedOperationException(String.valueOf(opCode)); // reserved
        }
        throw new UnsupportedOperationException(String.valueOf(opCode));
    }
}
