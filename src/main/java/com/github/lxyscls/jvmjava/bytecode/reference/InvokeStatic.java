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
            for (int i = method.getArgCount()-1; i >= 0; i--) {
                newFrame.getLocalVars().setObject(i, frame.getOperandStack().popObject());
            }
            /*
            if (method.isNative()) {
                if ("registerNatives".equals(method.getName())) {
                    System.out.printf("native method: %s %s %s\n", 
                            method.getBelongClass().getClassName(),
                            method.getName(), method.getDescriptor());                    
                    frame.getThread().popFrame();
                } else {
                    System.err.printf("native method: %s %s %s\n", 
                            method.getBelongClass().getClassName(),
                            method.getName(), method.getDescriptor());
                    System.exit(-1);
                }
            }*/            
        } catch (IOException | IllegalAccessException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
    
}
