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
public class Jobject implements Cloneable {
    Jclass _class;
    Object[] fields;
    Object[] array;
    Jclass extra;
    
    public Jobject(Jclass cls, int instanceFieldCount) {
        this._class = cls;
        this.fields = new Object[instanceFieldCount];
        this.array = null;
    }
    
    public Jobject(Jclass cls, Object[] array) {
        this._class = cls;
        this.array = array;
        this.fields = null;
    }
    
    public Object[] getFields() {
        return this.fields;
    }
    
    public Object[] getArray() {
        return this.array;
    }
    
    public void setArray(Object[] array) {
        this.array = array;
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
    
    public void setRefVar(String name, String descriptor, Object obj) {
        fields[_class.getInstanceField(name, descriptor).getSlotId()] = obj;
    }
    
    public Object getRefVar(String name, String descriptor) {
        return fields[_class.getInstanceField(name, descriptor).getSlotId()];
    }
    
    public void setExtra(Jclass cls) {
        this.extra = cls;
    }
    
    public Jclass getExtra() {
        return this.extra;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
