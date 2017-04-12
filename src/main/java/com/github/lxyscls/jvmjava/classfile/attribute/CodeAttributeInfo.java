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
class ExceptionTableEntry {
    private final int startPc;
    private final int endPc;
    private final int handlePc;
    private final int catchType;
    
    ExceptionTableEntry(int startPc, int endPc, int handlePc, int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlePc = handlePc;
        this.catchType = catchType;
    }
}

public class CodeAttributeInfo extends AttributeInfo {
    private final ConstantPool cp;
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code;
    private final ExceptionTableEntry[] exceptionTable;
    private final AttributeInfo[] attributes;
    
    CodeAttributeInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        maxStack = reader.readUint16();
        maxLocals = reader.readUint16();
        code = reader.readBytes((int)reader.readUint32());
        
        int exceptionTableLen = reader.readUint16();
        exceptionTable = new ExceptionTableEntry[exceptionTableLen];
        for (ExceptionTableEntry entry : exceptionTable) {
            entry = new ExceptionTableEntry(reader.readUint16(), reader.readUint16(), 
                    reader.readUint16(), reader.readUint16());
        }
        
        attributes = AttributeInfos.readAttributes(reader, cp);
    } 
}
