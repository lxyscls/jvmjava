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
public class UnparseAttributeInfo extends AttributeInfo {
    String attrName;
    long attrLen;
    byte[] info;

    public UnparseAttributeInfo(ClassReader reader, String attrName, long attrLen) {
        this.attrName = attrName;
        this.attrLen = attrLen;
        info = reader.readBytes((int)attrLen);
    }
}
