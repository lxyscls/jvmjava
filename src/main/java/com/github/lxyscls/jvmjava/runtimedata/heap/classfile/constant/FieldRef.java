/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantFieldrefInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantMemberInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Field;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class FieldRef extends MemberRef {
    Field field;
    
    public FieldRef(ConstantPool cp, ConstantFieldrefInfo info) {
        super(cp, (ConstantMemberInfo)info);
    }
    
    public Field resolvedField() throws IOException, IllegalAccessException, NoSuchFieldException {
        if (field == null) {
            resolveFieldRef();
        }
        return field;
    }

    private void resolveFieldRef() throws IOException, IllegalAccessException, NoSuchFieldException {
        Jclass cls = resolvedClass();
        
        Field ret = lookupField(cls, this.name, this.descriptor);
        if (ret == null) {
            throw new NoSuchFieldException();
        }
        
        if (!ret.isAccessibleTo(this.cp.getBelongClass())) {
            throw new  IllegalAccessException();
        }
        this.field = ret;
    }

    private Field lookupField(Jclass cls, String name, String descriptor) {
        for (Field fd: cls.getFields()) {
            if (fd.getName().equals(name) && fd.getDescriptor().equals(descriptor)) {
                return fd;
            }
        }
        
        for (Jclass intf: cls.getInterfaceClasses()) {
            Field ret = lookupField(intf, name, descriptor);
            if (ret != null) {
                return ret;
            }
        }
        
        if (cls.getSuperClass() != null) {
            return lookupField(cls.getSuperClass(), name, descriptor);
        }
        return null;
    }
}
