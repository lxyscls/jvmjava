/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class SymRef {
    protected ConstantPool cp;
    protected String className;
    protected Jclass _class;
    
    public Jclass resolvedClass() throws IOException, IllegalAccessException {
        if (this._class == null) {
            resolveClassRef();
        }
        return this._class;
    }
    
    private void resolveClassRef() throws IOException, IllegalAccessException {
        if (this._class != null) {
            return;
        }
        
        Jclass cls = this.cp.getBelongClass();
        Jclass ret = cls.getClassLoader().loadClass(className);
        if (!ret.isAccessibleTo(cls)) {
            throw new IllegalAccessException();
        }
        this._class = ret;
    }
}
