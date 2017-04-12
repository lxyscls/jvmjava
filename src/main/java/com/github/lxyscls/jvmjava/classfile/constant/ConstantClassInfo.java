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
class ConstantClassInfo extends ConstantInfo {
    private final ConstantPool cp;
    private final int nameIndex;
    
    ConstantClassInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        nameIndex = reader.readUint16();
    }
    
    public String getName() {
        return cp.getUtf8(nameIndex);
    }
}
