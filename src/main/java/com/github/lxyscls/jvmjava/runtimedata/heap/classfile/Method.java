/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.CodeAttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.ExceptionTableEntry;
import com.github.lxyscls.jvmjava.classfile.attribute.LineNumberTableAttributeInfo;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sk-xinyilong
 */
public class Method extends ClassMember {
    private int maxLocals;
    private int maxStack;
    private byte[] code;
    private ExceptionTable eTable;
    private LineNumberTableAttributeInfo lineNumberTable;
    private MethodArgRet argRet;
    private final byte[] argAnnotationData;
    
    public Method(Jclass cls, MemberInfo info) {
        super(cls, info);
        argRet = new MethodArgParser(descriptor).parse();
        argAnnotationData = new byte[]{'a'};
        
        CodeAttributeInfo codeInfo = info.getCodeAttribute();
        if (codeInfo != null) {
            maxLocals = codeInfo.getMaxLocals();
            maxStack = codeInfo.getMaxStack();
            code = codeInfo.getCode();
            eTable = new ExceptionTable(codeInfo.getExceptionTable(),
                    _class.getConstantPool());
            for (AttributeInfo attr : codeInfo.getAttributes()) {
                if (attr instanceof LineNumberTableAttributeInfo) {
                    lineNumberTable = (LineNumberTableAttributeInfo)attr;
                }
            }
        }
        if (isNative()) {
            // Hack, all the args arg long or double
            maxLocals = getArgCount() * 2;
            maxStack = 4;
            eTable = new ExceptionTable(new ExceptionTableEntry[0], _class.getConstantPool());
            switch (argRet.getRetType().charAt(0)) {
                case 'J': code = new byte[] {(byte)0xfe, (byte)0xad}; break;                
                case 'F': code = new byte[] {(byte)0xfe, (byte)0xae}; break;
                case 'D': code = new byte[] {(byte)0xfe, (byte)0xaf}; break;
                case 'V': code = new byte[] {(byte)0xfe, (byte)0xb1}; break;
                case 'L': case '[': code = new byte[] {(byte)0xfe, (byte)0xb0}; break;
                default:  code = new byte[] {(byte)0xfe, (byte)0xac}; break;
            }
        }
    }
    
    public int getMaxLocals() {
        return this.maxLocals;
    }
    
    public int getMaxStack() {
        return this.maxStack;
    }
    
    public byte[] getCode() {
        return this.code;
    }
    
    public int getArgCount() {
        if (isStatic()) {
            return this.argRet.getArgCount();
        } else {
            return this.argRet.getArgCount()+1;
        }
    }
    
    public int findExceptionHandlerPc(Jclass extCls, int pc) {
        try {
            ExceptionHandler eh = eTable.findExceptionHandler(extCls, pc);
            if (eh != null) {
                return eh.getHanlderPc();
            }
            return -1;
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            return -1;
        }
    }

    public int getLineNumber(int pc) {
        if (isNative()) {
            return -2;
        } else if (lineNumberTable == null) {
            return -1;
        }
        
        return lineNumberTable.getLineNumber(pc);
    }
    
    // reflect
    public Jclass[] getArgTypes() {
        if (this.argRet.getArgCount() == 0) {
            return null;
        }
        
        String[] argTypes = this.argRet.getArgTypes();
        Jclass[] argClss = new Jclass[argTypes.length];
        for (int i = 0; i < argClss.length; i++) {
            try {
                String clsName = Jclass.toClassName(argTypes[i]);
                argClss[i] = getBelongClass().getClassLoader().loadClass(clsName);
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(-1);
            }
        }
        return argClss;
    }
    
    // reflect
    public Jclass getReturnType() {
        try {
            String retName = Jclass.toClassName(this.argRet.getRetType());
            return getBelongClass().getClassLoader().loadClass(retName);
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
        return null;    
    }
    
    // reflect
    public Jclass[] getExceptionTypes() {
        ExceptionHandler[] table = eTable.getExHandlerTable();
        if (table.length == 0) {
            return null;
        }
        
        Jclass[] expClss = new Jclass[table.length];
        for (int i = 0; i < expClss.length; i++) {
            try {
                expClss[i] = table[i].getCatchType().resolvedClass();
            } catch (IOException | IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(-1);
            }
        }
        return expClss;
    }
    
    public byte[] getArgAnnotationData() {
        return this.argAnnotationData;
    }
    
    public Method clone() {
        try {
            return (Method)super.clone();
        } catch (CloneNotSupportedException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
        return null;
    }
    
    // hack for constructor with return 'this'
    public void ctorAddAreturn() {
        byte[] _code = Arrays.copyOf(code, code.length+1);
        _code[_code.length-2] = (byte) 0x2a; // aload0
        _code[_code.length-1] = (byte) 0xb0; // areturn
        code = _code;
    }
}
