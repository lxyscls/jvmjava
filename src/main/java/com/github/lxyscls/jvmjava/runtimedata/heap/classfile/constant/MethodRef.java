/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantMemberInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantMethodrefInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;

/**
 *
 * @author sk-xinyilong
 */
public class MethodRef extends MemberRef {
    Method method;
    
    public MethodRef(ConstantPool cp, ConstantMethodrefInfo info) {
        super(cp, (ConstantMemberInfo)info);
    }   
}
