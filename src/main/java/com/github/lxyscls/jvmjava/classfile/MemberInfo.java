/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile;

import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfos;
import com.github.lxyscls.jvmjava.classfile.attribute.CodeAttributeInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantPool;

/**
 *
 * @author sk-xinyilong
 */
public class MemberInfo {
    private final ConstantPool cp;
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    AttributeInfo[] attributes;
    
    public MemberInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        accessFlags = reader.readUint16();
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
        attributes = AttributeInfos.readAttributes(reader, cp);
    }
     
    public String getName() {
        return cp.getUtf8(nameIndex);
    }
    
    public String getDescriptor() {
        return cp.getUtf8(descriptorIndex);
    }
    
    public int getAccessFlags() {
        return accessFlags;
    }
    
    public AttributeInfo getCodeAttribute() {
        for (AttributeInfo info : attributes) {
            if (info instanceof CodeAttributeInfo) {
                return info;
            }
        }
        return null;
    }
}

class MemberInfos {
    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        int n = reader.readUint16();
        MemberInfo[] members = new MemberInfo[n];
        for (int i = 0; i < n; i++) {
            members[i] = new MemberInfo(reader, cp);
        }
        return members;
    }    
}
