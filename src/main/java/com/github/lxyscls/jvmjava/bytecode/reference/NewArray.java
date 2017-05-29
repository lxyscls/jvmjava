/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

class Atype {
    public static final short T_BOOLEAN = 4;
    public static final short T_CHAR = 5;
    public static final short T_FLOAT = 6;
    public static final short T_DOUBLE = 7;
    public static final short T_BYTE = 8;
    public static final short T_SHORT = 9;
    public static final short T_INT = 10;
    public static final short T_LONG = 11;    
}

/**
 *
 * @author sk-xinyilong
 */
public class NewArray extends ByteCode {
    private short aType;
    
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        aType = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        
        try {
            Jclass cls = loadArrayClass(frame.getMethod().getBelongClass().getClassLoader(), aType);
            stack.pushRef(cls.newArray(count));
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }

    private static Jclass loadArrayClass(ClassLoader loader, short aType) throws IOException {
        switch (aType) {
            case Atype.T_BOOLEAN: return loader.loadClass("[Z");
            case Atype.T_CHAR: return loader.loadClass("[C");
            case Atype.T_FLOAT: return loader.loadClass("[F");
            case Atype.T_DOUBLE: return loader.loadClass("[D");
            case Atype.T_BYTE: return loader.loadClass("[B");
            case Atype.T_SHORT: return loader.loadClass("[S");
            case Atype.T_INT: return loader.loadClass("[I");
            case Atype.T_LONG: return loader.loadClass("[J");
            default: System.err.println("Error array type"); System.exit(-1);
        }
        return null;
    }
    
}
