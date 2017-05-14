/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.attribute;

import com.github.lxyscls.jvmjava.classfile.ClassReader;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantPool;

/**
 *
 * @author sk-xinyilong
 */
public class ConstantValueAttributeInfo extends AttributeInfo {
    private final ConstantPool cp;
    private final int constantValueIndex;
    
    public ConstantValueAttributeInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        constantValueIndex = reader.readUint16();
    }
    
    public int getConstantValueIndex() {
        return this.constantValueIndex;
    }
}
