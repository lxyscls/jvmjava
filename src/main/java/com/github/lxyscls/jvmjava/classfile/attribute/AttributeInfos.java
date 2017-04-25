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
public class AttributeInfos {
    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) {
        int n = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[n];
        for (int i = 0; i < n; i++) {
            attributes[i] = newAttributeInfo(reader, cp, 
                    cp.getUtf8(reader.readUint16()), reader.readUint32());
        }
        return attributes;
    }
    
    public static AttributeInfo newAttributeInfo(ClassReader reader, ConstantPool cp,
            String attrName, long attrLen) {
        switch (attrName) {
            case "Code": return new CodeAttributeInfo(reader, cp);
            case "ConstantValue": return new ConstantValueAttributeInfo(reader, cp);
            case "Deprecated": return new DeprecatedAttributeInfo(reader);
            case "Exceptions": return new ExceptionsAttributeInfo(reader);
            case "LineNumberTable": return new LineNumberTableAttributeInfo(reader);
            case "LocalVariableTable": return new LocalVariableTableAttributeInfo(reader);
            case "SourceFile": return new SourceFileAttributeInfo(reader, cp);
            case "Synthetic": return new SyntheticAttributeInfo(reader);
            default: return new UnparseAttributeInfo(reader, attrName, attrLen);
        }
    }
}
