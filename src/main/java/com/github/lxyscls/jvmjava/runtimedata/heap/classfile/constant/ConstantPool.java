/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantClassInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantDoubleInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantFieldrefInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantFloatInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantIntegerInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantInterfaceMethodrefInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantLongInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantMethodrefInfo;
import com.github.lxyscls.jvmjava.classfile.constant.ConstantStringInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;

/**
 *
 * @author sk-xinyilong
 */
public class ConstantPool {
    private final Jclass _class;
    private final Object[] consts;
    
    public ConstantPool(Jclass _class, com.github.lxyscls.jvmjava.classfile.constant.ConstantPool cfCp) {
        this._class = _class;
        
        int cpCount = cfCp.getPoolSize();
        consts = new Object[cpCount];
        for (int i = 0; i < cpCount; i++) {
            ConstantInfo info = cfCp.getConstantInfo(i);
            if (info instanceof ConstantIntegerInfo) {
                consts[i] = ((ConstantIntegerInfo) info).getValue();
            } else if (info instanceof ConstantLongInfo) {
                consts[i] = ((ConstantLongInfo) info).getValue();
                i++;
            } else if (info instanceof ConstantFloatInfo) {
                consts[i] = ((ConstantFloatInfo) info).getValue();
            } else if (info instanceof ConstantDoubleInfo) {
                consts[i] = ((ConstantDoubleInfo) info).getValue();
                i++;
            } else if (info instanceof ConstantStringInfo) {
                consts[i] = ((ConstantStringInfo) info).getString();
            } else if (info instanceof ConstantClassInfo) {
                consts[i] = new ClassRef(this, (ConstantClassInfo) info);
            } else if (info instanceof ConstantFieldrefInfo) {
                consts[i] = new FieldRef(this, (ConstantFieldrefInfo) info);
            } else if (info instanceof ConstantMethodrefInfo) {
                consts[i] = new MethodRef(this, (ConstantMethodrefInfo) info);
            } else if (info instanceof ConstantInterfaceMethodrefInfo) {
                consts[i] = new InterfaceMethodRef(this, (ConstantInterfaceMethodrefInfo) info);
            }
        }
    }
    
    public Object getConst(int constantValueIndex) {
        return consts[constantValueIndex];
    }
    
    public Jclass getBelongClass() {
        return this._class;
    }
}
