/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.FieldRef;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Field;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
class PutField extends Index16ByteCode{
    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        Jclass cls = method.getBelongClass();
        FieldRef fr = (FieldRef)cls.getConstantPool().getConst(index);
        
        try {
            Field field = fr.resolvedField();
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            
            if (field.isFinal()) {
                if (cls != field.getBelongClass() || method.getName().equals("<init>")) {
                    throw new IllegalAccessError();
                }
            }
            
            Object val = frame.getOperandStack().popObject();
            Jobject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                throw new NullPointerException();
            }
            ref.getFields()[field.getSlotId()] = val;
        } catch (IOException | IllegalAccessException | NoSuchFieldException ex) {
            System.err.println(ex);
            System.exit(-1);
        }    
    }
}

class GetField extends Index16ByteCode{
    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        Jclass cls = method.getBelongClass();
        FieldRef fr = (FieldRef)cls.getConstantPool().getConst(index);
        
        try {
            Field field = fr.resolvedField();
            if (field.isStatic()) {
                throw new IncompatibleClassChangeError();
            }
            
            if (field.isFinal()) {
                if (cls != field.getBelongClass() || method.getName().equals("<init>")) {
                    throw new IllegalAccessError();
                }
            }
            
            Jobject ref = frame.getOperandStack().popRef();
            if (ref == null) {
                throw new NullPointerException();
            }
            frame.getOperandStack().pushObject(ref.getFields()[field.getSlotId()]);
        } catch (IOException | IllegalAccessException | NoSuchFieldException ex) {
            System.err.println(ex);
            System.exit(-1);
        }    
    }
}
