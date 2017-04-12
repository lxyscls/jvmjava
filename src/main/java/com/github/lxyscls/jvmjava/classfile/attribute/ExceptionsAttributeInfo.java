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
public class ExceptionsAttributeInfo extends AttributeInfo {
    private final int[] exceptionIndexTable;

    public ExceptionsAttributeInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s();
    }
    
    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
