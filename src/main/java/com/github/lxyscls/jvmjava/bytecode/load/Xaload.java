/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.load;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
class Iaload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Integer[] array = (Integer[])arrRef.getArray();
        stack.pushInt(array[index]);
    }
    
}

class Laload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Long[] array = (Long[])arrRef.getArray();
        stack.pushLong(array[index]);
    }
    
}

class Faload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Float[] array = (Float[])arrRef.getArray();
        stack.pushFloat(array[index]);
    }
    
}

class Daload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Double[] array = (Double[])arrRef.getArray();
        stack.pushDouble(array[index]);
    }
    
}

class Aaload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Jobject[] array = (Jobject[])arrRef.getArray();
        stack.pushRef(array[index]);
    }
    
}

class Baload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Byte[] array = (Byte[])arrRef.getArray();
        stack.pushInt((int)array[index]);
    }
    
}

class Caload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Character[] array = (Character[])arrRef.getArray();
        stack.pushInt(Character.getNumericValue(array[index]));
    }
    
}

class Saload extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Short[] array = (Short[])arrRef.getArray();
        stack.pushInt((int)array[index]);
    }
    
}