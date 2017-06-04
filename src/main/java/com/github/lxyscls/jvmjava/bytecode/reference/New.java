/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class New extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getBelongClass().getConstantPool();
        ClassRef cr = (ClassRef)cp.getConst(index);
        try {
            Jclass cls = cr.resolvedClass();
            if (!cls.getInitStarted()) {
                frame.revertNextPc();
                cls.clInitClass(frame);
                return;
            }            
            
            if (cls.isAbstract() || cls.isInterface()) {
                throw new InstantiationError();
            }
            frame.getOperandStack().pushRef(cls.newObject());
        } catch (IOException | IllegalAccessException ex) {
           System.err.println(ex);
           System.exit(-1);
        }
    }
}
