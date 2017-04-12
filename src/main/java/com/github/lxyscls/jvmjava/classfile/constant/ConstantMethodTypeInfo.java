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
public class ConstantMethodTypeInfo extends ConstantInfo {
    private final ConstantPool cp;
    private final int descriptorIndex;
    
    public ConstantMethodTypeInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        descriptorIndex = reader.readUint16();
    }
}
