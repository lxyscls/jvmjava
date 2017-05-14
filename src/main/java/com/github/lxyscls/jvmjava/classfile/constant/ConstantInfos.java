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

public class ConstantInfos {
    static final short CONSTANT_CLASS = 7;
    static final short CONSTANT_FIELDREF = 9;
    static final short CONSTANT_METHODREF = 10;
    static final short CONSTANT_INTERFACEMETHODREF = 11;
    static final short CONSTANT_STRING = 8;
    static final short CONSTANT_INTEGER = 3;
    static final short CONSTANT_FLOAT = 4;
    static final short CONSTANT_LONG = 5;
    static final short CONSTANT_DOUBLE = 6;
    static final short CONSTANT_NAMEANDTYPE = 12;
    static final short CONSTANT_UTF8 = 1;
    static final short CONSTANT_METHODHANDLE = 15;
    static final short CONSTANT_METHODTYPE = 16;
    static final short CONSTANT_INVOKEDYNAMIC = 18;

    public static ConstantInfo newConstantInfo(short tag, ClassReader reader, ConstantPool cp) throws IOException {
        switch (tag) {
            case CONSTANT_CLASS: return new ConstantClassInfo(reader, cp);
            case CONSTANT_FIELDREF: return new ConstantFieldrefInfo(reader, cp);
            case CONSTANT_METHODREF: return new ConstantMethodrefInfo(reader, cp);
            case CONSTANT_INTERFACEMETHODREF: return new ConstantInterfaceMethodrefInfo(reader, cp);
            case CONSTANT_STRING: return new ConstantStringInfo(reader, cp);
            case CONSTANT_INTEGER: return new ConstantIntegerInfo(reader);
            case CONSTANT_FLOAT: return new ConstantFloatInfo(reader);
            case CONSTANT_LONG: return new ConstantLongInfo(reader);
            case CONSTANT_DOUBLE: return new ConstantDoubleInfo(reader);
            case CONSTANT_NAMEANDTYPE: return new ConstantNameAndTypeInfo(reader, cp);
            case CONSTANT_UTF8: return new ConstantUtf8Info(reader);
            case CONSTANT_METHODHANDLE: return new ConstantMethodHandleInfo(reader, cp);
            case CONSTANT_METHODTYPE: return new ConstantMethodTypeInfo(reader, cp);
            case CONSTANT_INVOKEDYNAMIC: return new ConstantInvokeDynamicInfo(reader, cp);
        }
        throw new ClassFormatError("Invalid constant info tag!");
    }
}

