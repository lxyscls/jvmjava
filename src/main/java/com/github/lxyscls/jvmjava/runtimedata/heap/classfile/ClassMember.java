/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.runtimedata.heap.AccessFlags;

/**
 *
 * @author sk-xinyilong
 */
public class ClassMember implements Cloneable {
    protected final int accessFlags;
    protected final String name;
    protected final String descriptor;
    protected final String signature;
    protected final byte[] annotationData;
    protected Jclass _class;
    
    public ClassMember(Jclass cls, MemberInfo info) {
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();
        signature = "sig";
        annotationData = new byte[]{'a'};
        _class = cls;
    }
    
    public boolean isPublic() {
        return (accessFlags & AccessFlags.ACC_PUBLIC) != 0;
    }
    
    public boolean isPrivate() {
        return (accessFlags & AccessFlags.ACC_PRIVATE) != 0;
    }
    
    public boolean isProtected() {
        return (accessFlags & AccessFlags.ACC_PROTECTED) != 0;
    }
    
    public boolean isStatic() {
        return (accessFlags & AccessFlags.ACC_STATIC) != 0;
    }
    
    public boolean isFinal() {
        return (accessFlags & AccessFlags.ACC_FINAL) != 0;
    }
    
    public boolean isSynthetic() {
        return (accessFlags & AccessFlags.ACC_SYNTHETIC) != 0;
    }
    
    public boolean isAbstract() {
        return (accessFlags & AccessFlags.ACC_ABSTRACT) != 0;
    }    
    
    public boolean isNative() {
        return (accessFlags & AccessFlags.ACC_NATIVE) != 0;
    }
    
    public Jclass getBelongClass() {
        return this._class;
    }

    public String getName() {
        return this.name;
    }
    
    public String getDescriptor() {
        return this.descriptor;
    }
    
    public int getAccessFlags() {
        return this.accessFlags;
    }
    
    public String getSignature() {
        return this.signature;
    }
    
    public byte[] getAnnotationData() {
        return this.annotationData;
    }
     
    public boolean isAccessibleTo(Jclass cls) {
        if (isPublic()) {
            return true;
        }
        
        if (isProtected()) {
            return cls == this._class || cls.isSubClassOf(this._class) ||
                    cls.getPackageName().equals(this._class.getPackageName());
        }
        
        if (!isPrivate()) {
            return cls.getPackageName().equals(this._class.getPackageName());
        }
        return cls == this._class;
    }
}
