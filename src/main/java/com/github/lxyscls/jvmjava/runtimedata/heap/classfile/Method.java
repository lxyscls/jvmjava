/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.AttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.CodeAttributeInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.LineNumberTableAttributeInfo;
import java.io.IOException;

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
    
    public Method(Jclass cls, MemberInfo info) {
        super(cls, info);
        argRet = new MethodArgParser(descriptor).parse();
        
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
            maxLocals = getArgCount();
            maxStack = 4;
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
}
