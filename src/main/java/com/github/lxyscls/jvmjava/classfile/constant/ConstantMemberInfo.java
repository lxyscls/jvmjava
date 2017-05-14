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
public class ConstantMemberInfo extends ConstantInfo {
    private final ConstantPool cp;
    private final int classIndex;
    private final int nameAndTypeIndex;

    ConstantMemberInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }
    
    public String getClassName() {
        return cp.getUtf8(classIndex);
    }
    
    public String[] getNameAndType() {
        return cp.getNameAndType(nameAndTypeIndex);
    }
}
