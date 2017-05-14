/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantClassInfo;

/**
 *
 * @author sk-xinyilong
 */
public class ClassRef extends SymRef {
    public ClassRef(ConstantPool cp, ConstantClassInfo info) {
        this.cp = cp;
        this.className = info.getName();
        this._class = null;
    }
}
