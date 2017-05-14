/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile;

import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfos;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantPool;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class ClassFile {
    private long magic;
    private int minorVersion;
    private int majorVersion;
    private final ConstantPool cp;
    private final int accessFlags;
    private final int thisClass;
    private final int superClass;
    private final int[] interfaces;
    private final MemberInfo[] fields;
    private final MemberInfo[] methods;
    private final AttributeInfo[] attributes;
    
    public ClassFile(ClassReader reader) throws IOException {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        cp = new ConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = MemberInfos.readMembers(reader, cp);
        methods = MemberInfos.readMembers(reader, cp);
        attributes = AttributeInfos.readAttributes(reader, cp);
    }
    
    private void readAndCheckMagic(ClassReader reader) {
        magic = reader.readUint32();
        if (magic != 0xCAFEBABEL) {
            throw new ClassFormatError("Invalid magic!");
        }
    }
    
    private void readAndCheckVersion(ClassReader reader) {
        minorVersion = reader.readUint16();
        majorVersion = reader.readUint16();
        switch(majorVersion) {
            case 45:
                break;
            case 46: case 47: case 48: case 49: case 50: case 51: case 52:
                if (minorVersion != 0) {
                    throw new UnsupportedClassVersionError(
                            Integer.toString(majorVersion) + Integer.toString(minorVersion));
                }
                break;
        }
    }
    
    public int getMinorVersion() {
        return minorVersion;
    }
    
    public int getMajorVersion() {
        return majorVersion;
    }
    
    public ConstantPool getConstantPool() {
        return cp;
    }
    
    public int getAccessFlags() {
        return accessFlags;
    }
    
    public MemberInfo[] getFields() {
        return fields;
    }
    
    public MemberInfo[] getMethods() {
        return methods;
    }
    
    public String getClassName() {
        return cp.getClassName(thisClass);
    }
    
    public String getSuperClassName() {
        if (superClass > 0) {
            return cp.getClassName(superClass);
        }
        return "";
    }
    
    public String[] getInterfaceNames() {
        String[] intfNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            intfNames[i] = cp.getClassName(interfaces[i]);
        }
        return intfNames;
    }
}
