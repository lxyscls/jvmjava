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
public class ConstantMethodHandleInfo extends ConstantInfo {
    private final ConstantPool cp;
    private final short referKind;
    private final int referIndex;
    
    public ConstantMethodHandleInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        referKind = reader.readUint8();
        referIndex = reader.readUint16();
    }  
}
