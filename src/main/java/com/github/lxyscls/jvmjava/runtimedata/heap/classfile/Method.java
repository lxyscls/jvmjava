/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.CodeAttributeInfo;

/**
 *
 * @author sk-xinyilong
 */
public class Method extends ClassMember {
    private final int maxLocals;
    private final int maxStack;
    private final byte[] code;
    private final MethodArgRet argRet;
    
    public Method(MemberInfo info) {
        super(info);
        argRet = new MethodArgParser(descriptor).parse();
        
        CodeAttributeInfo codeInfo = info.getCodeAttribute();
        if (codeInfo != null) {
            this.maxLocals = codeInfo.getMaxLocals();
            this.maxStack = codeInfo.getMaxStack();
            this.code = codeInfo.getCode();
        } else {
            this.maxLocals = 0;
            this.maxStack = 0;
            this.code = null;
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
        return this.argRet.getArgCount();
    }
}
