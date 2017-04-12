/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.ClassReader;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class ConstantPool {
    private final ConstantInfo[] infoPool;
    
    public ConstantPool(ClassReader reader) throws IOException {
        int n = reader.readUint16();
        infoPool = new ConstantInfo[n];
        for (int i = 1; i < n; i++) {
            infoPool[i] = ConstantInfos.newConstantInfo(reader.readUint8(), reader, this);
            if (infoPool[i] instanceof ConstantDoubleInfo ||
                    infoPool[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
    }
    
    public ConstantInfo getConstantInfo(int index) {
        if (infoPool[index] != null) {
            return infoPool[index];
        }
        throw new ClassFormatError("Invalid constant pool index!");
    }
    
    public String[] getNameAndType(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo)getConstantInfo(index);
        return info.getNameAndType();
    }
    
    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo)getConstantInfo(index);
        return info.getName();
    }
    
    public String getUtf8(int index) {
        ConstantUtf8Info info = (ConstantUtf8Info)getConstantInfo(index);
        return info.getUtf8();
    }
    
    public int getPoolSize() {
        return infoPool.length;
    }
}
