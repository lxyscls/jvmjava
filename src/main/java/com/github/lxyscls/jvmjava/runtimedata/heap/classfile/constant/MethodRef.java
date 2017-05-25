/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantMemberInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantMethodrefInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class MethodRef extends MemberRef {
    Method method;
    
    public MethodRef(ConstantPool cp, ConstantMethodrefInfo info) {
        super(cp, (ConstantMemberInfo)info);
    }
    
    public Method resolvedMethod() throws IOException, IllegalAccessException {
        if (this.method == null) {
            resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef() throws IOException, IllegalAccessException {
        Jclass cls = this.resolvedClass();
        
        if (cls.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        
        Method method = lookupMethod(cls, name, descriptor);
        if (method == null) {
            throw new NoSuchMethodError();
        }
        
        if (!method.isAccessibleTo(this.cp.getBelongClass())) {
            throw new IllegalAccessError();
        }
        this.method = method;
    }

    private Method lookupMethod(Jclass cls, String name, String descriptor) {
        Method method = MethodLookup.lookupMethodInClass(cls, name, descriptor);
        if (method == null) {
            method = MethodLookup.lookupMethodInInterfaces(cls.getInterfaceClasses(), name, descriptor);
        }
        return method;
    }
}
