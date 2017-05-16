/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Field;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.FieldRef;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
class PutStatic extends Index16ByteCode {

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        Jclass cls = method.getBelongClass();
        FieldRef fr = (FieldRef)cls.getConstantPool().getConst(index);
        
        try {
            Field field = fr.resolvedField();
            if (!field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            
            if (field.isFinal()) {
                if (cls != field.getBelongClass() || method.getName().equals("<cinit>")) {
                    throw new IllegalAccessError();
                }
            }
            
            cls.getStaticVars()[field.getSlotId()] = frame.getOperandStack().popObject();
        } catch (IOException | IllegalAccessException | NoSuchFieldException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}

class GetStatic extends Index16ByteCode {

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        Jclass cls = method.getBelongClass();
        FieldRef fr = (FieldRef)cls.getConstantPool().getConst(index);
        
        try {
            Field field = fr.resolvedField();
            if (!field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }

            frame.getOperandStack().pushObject(field.getBelongClass()
                    .getStaticVars()[field.getSlotId()]);
        } catch (IOException | IllegalAccessException | NoSuchFieldException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }
}
