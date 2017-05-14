/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantMemberInfo;

/**
 *
 * @author sk-xinyilong
 */
public class MemberRef extends SymRef{
    protected final String name;
    protected final String descriptor;
    
    public MemberRef(ConstantPool cp, ConstantMemberInfo info) {
        this.cp = cp;
        this.className = info.getClassName();
        this._class = null;
        this.name = info.getNameAndType()[0];
        this.descriptor = info.getNameAndType()[1];
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescriptor() {
        return this.descriptor;
    }
}
