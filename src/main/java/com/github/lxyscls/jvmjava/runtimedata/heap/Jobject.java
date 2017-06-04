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
    Object[] array;
    Jclass extra;
    
    public Jobject(Jclass cls, int instanceFieldCount) {
        this._class = cls;
        fields = new Object[instanceFieldCount];
    }
    
    public Jobject(Jclass cls, Object[] array) {
        this._class = cls;
        this.array = array;
    }
    
    public Object[] getFields() {
        return this.fields;
    }
    
    public Object[] getArray() {
        return this.array;
    }
    
    public int getArrayLength() {
        return this.array.length;
    }
    
    public Jclass getBelongClass() {
        return this._class;
    }

    public boolean isInstanceOf(Jclass cls) {
        return cls.isAssignableFrom(this._class);
    }
    
    public void setRefVar(String name, String descriptor, Jobject obj) {
        fields[_class.getInstanceField(name, descriptor).getSlotId()] = obj;
    }
    
    public Jobject getRefVar(String name, String descriptor) {
        return (Jobject)fields[_class.getInstanceField(name, descriptor).getSlotId()];
    }
    
    public void setExtra(Jclass cls) {
        this.extra = cls;
    }
    
    public Jclass getExtra() {
        return this.extra;
    }
}
