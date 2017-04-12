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
class ConstantNameAndTypeInfo extends ConstantInfo {
    private final int nameIndex;
    private final int descriptorIndex;
    private final ConstantPool cp;
    
    ConstantNameAndTypeInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();        
    }

    public String[] getNameAndType() {
        return new String[] { cp.getUtf8(nameIndex), cp.getUtf8(descriptorIndex) };
    }
}
