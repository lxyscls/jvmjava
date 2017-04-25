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
    public static ByteCode newControl(short opCode) {
        switch (opCode) {
            case 0xa7: return new Goto();
            case 0xaa: return new TableSwitch();
            case 0xab: return new LookupSwitch();
        }
        return null;
    }
}
