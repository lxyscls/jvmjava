/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.InterfaceMethodRef;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class InvokeInterface extends Index16ByteCode {
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        super.fetchOperands(reader);
        reader.readUint8();
        reader.readUint8();
    }
            
    @Override
    public void execute(Frame frame) {
        Jclass cls = frame.getMethod().getBelongClass();
        InterfaceMethodRef mr = (InterfaceMethodRef)cls.getConstantPool().getConst(index);
        try {
            Method method = mr.resolvedInterfaceMethod();
            Jclass mcls = mr.resolvedClass();
            
            if (method.isStatic() || method.isPrivate()) {
                throw new IncompatibleClassChangeError();
            }
            
            Object[] slots = new Object[method.getArgCount()];
            for (int i = slots.length-1; i >= 0; i--) {
                slots[i] = frame.getOperandStack().popObject();
            }
            Jobject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                throw new NullPointerException();
            }
            
            if (!ref.getBelongClass().isImplements(mcls)) {
                throw new IncompatibleClassChangeError();
            }
            
            method = MethodLookup.lookupMethodInClass(ref.getBelongClass(), 
                mr.getName(), mr.getDescriptor());
            if (method == null || method.isAbstract()) {
                throw new AbstractMethodError();
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
