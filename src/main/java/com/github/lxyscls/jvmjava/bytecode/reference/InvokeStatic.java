/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class InvokeStatic extends Index16ByteCode {

    @Override
    public void execute(Frame frame) {
        Jclass cls = frame.getMethod().getBelongClass();
        MethodRef mr = (MethodRef)cls.getConstantPool().getConst(index);
        try {
            Method method = mr.resolvedMethod();
            Jclass mcls = method.getBelongClass();
            if (!mcls.getInitStarted()) {
                frame.revertNextPc();
                mcls.clInitClass(frame);
                return;
            }
                        
            if (!method.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            
            Frame newFrame = new Frame(frame.getThread(), method);
            frame.getThread().pushFrame(newFrame);
            
            Object[] slots = new Object[method.getArgCount()];
            for (int i = slots.length-1; i >= 0; i--) {
                slots[i] = frame.getOperandStack().popObject();
            }
            int i = 0;
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
