/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.ClassReader;

/**
 *
 * @author sk-xinyilong
 */
public class ConstantStringInfo extends ConstantInfo {
    private final int stringIndex;
    private final ConstantPool cp;

    ConstantStringInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        stringIndex = reader.readUint16();
    }

    public String getString() {
        return cp.getUtf8(stringIndex);
    }  
}
