/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodes;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.Jthread;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class Interpreter {
    public static void interpret(Method method, String[] args) {
        Jthread thread = new Jthread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);
        
        frame.getLocalVars().setRef(0, 
                createArgsArrayRef(method.getBelongClass().getClassLoader(), args));
        
        loop(thread);
    }
    
    static Jobject createArgsArrayRef(ClassLoader loader, String[] args) {
        try {
            Jclass cls = loader.loadClass("java/lang/String").newArrayClass();
            Jobject obj = cls.newArray(args.length);
            Object[] array = obj.getArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = Jstring.stringToInternObject(loader, args[i]);
            }
            return obj;
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
        return null;
    }
    
    static void loop(Jthread thread) {
        ByteCodeReader reader = new ByteCodeReader();
        
        while (true) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);           
            try {
                reader.reset(frame.getMethod().getCode(), pc);
                short opcode = reader.readUint8();
                ByteCode bc = ByteCodes.newByteCode(opcode);
                if (bc == null) {
                    throw new UnsupportedOperationException();
                }
                bc.fetchOperands(reader);
                frame.setNextPc(reader.getPc());

                System.out.printf("%s.%s() pc: %2d bytecode: %s val: %s\n", 
                        frame.getMethod().getBelongClass().getClassName(),
                        frame.getMethod().getName(),
                        pc, bc.getClass().getSimpleName(), bc.toString());
     
                bc.execute(frame);
                if (thread.isStackEmpty()) {
                    break;
                }
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex);
                System.out.printf("localVars: %s\n", frame.getLocalVars());
                System.out.printf("operandStack: %s\n", frame.getOperandStack());
                break;
            }
        }
    }
}
