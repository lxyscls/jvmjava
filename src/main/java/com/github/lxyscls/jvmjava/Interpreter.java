/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import com.github.lxyscls.jvmjava.bytecode.base.ByteCode;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodeReader;
import com.github.lxyscls.jvmjava.bytecode.base.ByteCodes;
import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classfile.attribute.CodeAttributeInfo;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.Jthread;

/**
 *
 * @author sk-xinyilong
 */
public class Interpreter {
    public static void interpret(MemberInfo methodInfo) {
        CodeAttributeInfo info = (CodeAttributeInfo)methodInfo.getCodeAttribute();
        Jthread thread = new Jthread();
        Frame frame = new Frame(thread, info.getMaxLocals(), info.getMaxStack());
        thread.pushFrame(frame);
        
        loop(thread, info.getCode());
    }
    
    static void loop(Jthread thread, byte[] code) {
        Frame frame = thread.popFrame();
        ByteCodeReader reader = new ByteCodeReader();
        
        while (true) {
            try {
                int pc = frame.getNextPc();
                thread.setPc(pc);

                reader.reset(code, pc);
                short opcode = reader.readUint8();
                ByteCode bc = ByteCodes.newByteCode(opcode);
                bc.fetchOperands(reader);
                frame.setNextPc(reader.getPc());

                System.out.printf("pc: %2d bytecode: %s val: %s\n", pc, bc.getClass().getSimpleName(), bc.toString());
                bc.execute(frame);
            } catch (UnsupportedOperationException ex) {
                System.out.printf("localVars: %s\n", frame.getLocalVars());
                System.out.printf("operandStack: %s\n", frame.getOperandStack());
                break;
            }
        }
    }
}
