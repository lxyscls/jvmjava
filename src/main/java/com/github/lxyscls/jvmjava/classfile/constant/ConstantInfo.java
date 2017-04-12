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

public abstract class ConstantInfo {
}

class ConstantInfos {
    static final short CONSTANT_Class = 7;
    static final short CONSTANT_Fieldref = 9;
    static final short CONSTANT_Methodref = 10;
    static final short CONSTANT_InterfaceMethodref = 11;
    static final short CONSTANT_String = 8;
    static final short CONSTANT_Integer = 3;
    static final short CONSTANT_Float = 4;
    static final short CONSTANT_Long = 5;
    static final short CONSTANT_Double = 6;
    static final short CONSTANT_NameAndType = 12;
    static final short CONSTANT_Utf8 = 1;
    static final short CONSTANT_MethodHandle = 15;
    static final short CONSTANT_MethodType = 16;
    static final short CONSTANT_InvokeDynamic = 18;

    public static ConstantInfo newConstantInfo(short tag, ClassReader reader, ConstantPool cp) throws IOException {
        switch (tag) {
            case CONSTANT_Class: return new ConstantClassInfo(reader, cp);
            case CONSTANT_Fieldref: return new ConstantFieldrefInfo(reader, cp);
            case CONSTANT_Methodref: return new ConstantMethodrefInfo(reader, cp);
            case CONSTANT_InterfaceMethodref: return new ConstantInterfaceMethodrefInfo(reader, cp);
            case CONSTANT_String: return new ConstantStringInfo(reader, cp);
            case CONSTANT_Integer: return new ConstantIntegerInfo(reader);
            case CONSTANT_Float: return new ConstantFloatInfo(reader);
            case CONSTANT_Long: return new ConstantLongInfo(reader);
            case CONSTANT_Double: return new ConstantDoubleInfo(reader);
            case CONSTANT_NameAndType: return new ConstantNameAndTypeInfo(reader, cp);
            case CONSTANT_Utf8: return new ConstantUtf8Info(reader);
            case CONSTANT_MethodHandle: return new ConstantMethodHandleInfo(reader, cp);
            case CONSTANT_MethodType: return new ConstantMethodTypeInfo(reader, cp);
            case CONSTANT_InvokeDynamic: return new ConstantInvokeDynamicInfo(reader, cp);
        }
        throw new ClassFormatError("Invalid constant info tag!");
    }
}
