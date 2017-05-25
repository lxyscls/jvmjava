/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantInterfaceMethodrefInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantMemberInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class InterfaceMethodRef extends MemberRef {
    Method method;
    
    public InterfaceMethodRef(ConstantPool cp, ConstantInterfaceMethodrefInfo info) {
        super(cp, (ConstantMemberInfo)info);
    }
    
    public Method resolvedInterfaceMethod() throws IOException, IllegalAccessException {
        if (this.method == null) {
            resolveInterfaceMethodRef();
        }
        return this.method;
    }

    private void resolveInterfaceMethodRef() throws IOException, IllegalAccessException {
        Jclass cls = this.resolvedClass();
        
        if (!cls.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        
        Method method = lookupInterfaceMethod(cls, name, descriptor);
        if (method == null) {
            throw new NoSuchMethodError();
        }
        
        if (!method.isAccessibleTo(this.cp.getBelongClass())) {
            throw new IllegalAccessError();
        }
        this.method = method;         
    }

    private Method lookupInterfaceMethod(Jclass cls, String name, String descriptor) {
        for (Method method: cls.getMethods()) {
            if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(cls.getInterfaceClasses(), name, descriptor);
    }
}
