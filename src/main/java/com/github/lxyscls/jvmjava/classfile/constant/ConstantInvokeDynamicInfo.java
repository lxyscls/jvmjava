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
public class ConstantInvokeDynamicInfo extends ConstantInfo {
    private final ConstantPool cp;
    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;
    
    public ConstantInvokeDynamicInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        bootstrapMethodAttrIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }  
}
