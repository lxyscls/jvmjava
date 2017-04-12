/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.attribute;

import com.github.lxyscls.jvmjava.classfile.ClassReader;

/**
 *
 * @author sk-xinyilong
 */
class LocalVariableTableEntry {
    private final int startPc;
    private final int length;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int index;
    
    LocalVariableTableEntry(int startPc, int length, int nameIndex, 
            int descriptorIndex, int index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.index = index;
    }
}

public class LocalVariableTableAttributeInfo extends AttributeInfo {
    private final LocalVariableTableEntry[] localVariableTable;

    public LocalVariableTableAttributeInfo(ClassReader reader) {
        int localVariableTableLen = reader.readUint16();
        localVariableTable = new LocalVariableTableEntry[localVariableTableLen];
        for (LocalVariableTableEntry entry : localVariableTable) {
            entry = new LocalVariableTableEntry(reader.readUint16(), 
                    reader.readUint16(), reader.readUint16(), 
                    reader.readUint16(), reader.readUint16());
        }
    }
    
}
