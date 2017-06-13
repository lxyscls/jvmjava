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
public class CodeAttributeInfo extends AttributeInfo {
    private final ConstantPool cp;
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code;
    private final ExceptionTableEntry[] eTable;
    private final AttributeInfo[] attributes;
    
    CodeAttributeInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        maxStack = reader.readUint16();
        maxLocals = reader.readUint16();
        code = reader.readBytes((int)reader.readUint32());
        
        int exceptionTableLen = reader.readUint16();
        eTable = new ExceptionTableEntry[exceptionTableLen];
        for (int i = 0; i < eTable.length; i++) {
            eTable[i] = new ExceptionTableEntry(reader.readUint16(), reader.readUint16(), 
                    reader.readUint16(), reader.readUint16());
        }
        
        attributes = AttributeInfos.readAttributes(reader, cp);
    }
    
    public int getMaxStack() {
        return maxStack;
    }
    
    public int getMaxLocals() {
        return maxLocals;
    }
    
    public byte[] getCode() {
        return code;
    }
    
    public ExceptionTableEntry[] getExceptionTable() {
        return eTable;
    }
    
    public AttributeInfo[] getAttributes() {
        return this.attributes;
    }
}
