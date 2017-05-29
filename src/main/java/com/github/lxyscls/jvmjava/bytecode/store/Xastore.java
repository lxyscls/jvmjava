/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.store;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
class Iastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        Integer value = stack.popInt();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Lastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        Long value = stack.popLong();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Fastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        Float value = stack.popFloat();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Dastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        Double value = stack.popDouble();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Aastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        Jobject value = stack.popRef();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Bastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int value = stack.popInt();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Castore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int value = stack.popInt();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}

class Sastore extends NoOperandByteCode{

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        
        int value = stack.popInt();
        int index = stack.popInt();
        Jobject arrRef = stack.popRef();
        
        if (arrRef == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= arrRef.getArrayLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        arrRef.getArray()[index] = value;
    }
    
}