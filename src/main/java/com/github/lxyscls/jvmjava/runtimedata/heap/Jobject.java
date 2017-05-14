/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;

/**
 *
 * @author sk-xinyilong
 */
public class Jobject {
    Jclass _class;
    Object[] fields;
    
    public Jobject(Jclass cls, int instanceFieldCount) {
        this._class = cls;
        fields = new Object[instanceFieldCount];
    }
    
    public Object[] getFields() {
        return this.fields;
    }

    public boolean isInstanceOf(Jclass cls) {
        return cls.isAssignableFrom(this._class);
    }
}
