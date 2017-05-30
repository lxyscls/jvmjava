/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.constant;

import com.github.lxyscls.jvmjava.bytecode.base.Index16ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.Index8ByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;

/**
 *
 * @author sk-xinyilong
 */
class Ldc extends Index8ByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getBelongClass().getConstantPool();
        Object obj = cp.getConst(index);
        if (obj instanceof String) {
            stack.pushRef(Jstring.stringToInternObject(frame.getMethod().
                    getBelongClass().getClassLoader(), (String)obj));
        } else {
            stack.pushObject(obj);
        }
    }
}

class Ldc_w extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getBelongClass().getConstantPool();
        Object obj = cp.getConst(index);
        if (obj instanceof String) {
            stack.pushRef(Jstring.stringToInternObject(frame.getMethod().
                    getBelongClass().getClassLoader(), (String)obj));
        } else {
            stack.pushObject(obj);
        }
    }
}

class Ldc2_w extends Index16ByteCode {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getBelongClass().getConstantPool();
        if (cp.getConst(index) instanceof Long || 
                cp.getConst(index) instanceof Double) {
            stack.pushObject(cp.getConst(index));        
        }
    }
}
