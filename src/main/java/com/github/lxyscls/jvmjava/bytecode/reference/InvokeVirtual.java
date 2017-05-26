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

            Object[] slots = new Object[method.getArgCount()];
            for (int i = slots.length-1; i >= 0; i--) {
                slots[i] = frame.getOperandStack().popObject();
            }
            Jobject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                if (mr.getName().equals("println")) {
                    switch (mr.getDescriptor()) {
                        case "(Z)V": System.out.println((Integer)slots[0] != 0); break;
                        case "(C)V": System.out.println((Integer)slots[0]); break;
                        case "(B)V": System.out.println((Integer)slots[0]); break;
                        case "(S)V": System.out.println((Integer)slots[0]); break;
                        case "(I)V": System.out.println((Integer)slots[0]); break;
                        case "(J)V": System.out.println((Long)slots[0]); break;
                        case "(F)V": System.out.println((Float)slots[0]); break;
                        case "(D)V": System.out.println((Double)slots[0]); break;
                        default: throw new NoSuchMethodError(mr.getDescriptor());
                    }
                    return;
                }
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
            if (!method.isPublic()) {
                throw new IllegalAccessError();
            }
            
            Frame newFrame = new Frame(frame.getThread(), method);
            frame.getThread().pushFrame(newFrame);
            newFrame.getLocalVars().setObject(0, ref);
            for (int i = 0; i < slots.length; i++) {
                newFrame.getLocalVars().setObject(i+1, slots[i]);
            }     
            
            if (method.isNative()) {
                if ("registerNatives".equals(method.getName())) {
                    System.out.printf("native method: %s %s %s\n", 
                            method.getBelongClass().getClassName(),
                            method.getName(), method.getDescriptor());                        
                    frame.getThread().popFrame();
                } else {
                    System.err.printf("native method: %s %s %s", 
                            method.getBelongClass().getClassName(),
                            method.getName(), method.getDescriptor());
                    System.exit(-1);
                }
            }            
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}
