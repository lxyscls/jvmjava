/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class InvokeVirtual extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        Jclass cls = frame.getMethod().getBelongClass();
        MethodRef mr = (MethodRef)cls.getConstantPool().getConst(index);
        try {
            Method method = mr.resolvedMethod();
            Jclass mcls = mr.resolvedClass();
            
            if (method.isStatic()) {
                throw new IncompatibleClassChangeError();
            }

            Object[] slots = new Object[method.getArgCount()-1];
            for (int i = slots.length-1; i >= 0; i--) {
                slots[i] = frame.getOperandStack().popObject();
            }
            Jobject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                throw new NullPointerException();
            }

            if (method.isProtected() && mcls.isSuperClassof(cls) &&
                    !mcls.getPackageName().equals(cls.getPackageName()) &&
                    ref.getBelongClass() != cls && 
                    !ref.getBelongClass().isSubClassOf(cls)) {
                throw new IllegalAccessError();
            }
            
            method = MethodLookup.lookupMethodInClass(ref.getBelongClass(), 
                    mr.getName(), mr.getDescriptor());
            if (method == null || method.isAbstract()) {
                throw new AbstractMethodError();
            }
            
            Frame newFrame = new Frame(frame.getThread(), method);
            frame.getThread().pushFrame(newFrame);
            
            newFrame.getLocalVars().setObject(0, ref);
            int i = 1;
            for (Object obj : slots) {
                newFrame.getLocalVars().setObject(i, obj);
                if (obj instanceof Long || obj instanceof Double) {
                    i += 2;
                } else {
                    i += 1;                    
                }
            }            
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}
