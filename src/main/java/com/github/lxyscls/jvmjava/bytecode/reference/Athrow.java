/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.bytecode.reference;

import com.github.lxyscls.jvmjava.bytecode.base.NoOperandByteCode;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.Jthread;
import com.github.lxyscls.jvmjava.runtimedata.OperandStack;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.nativemethod.java.lang.StackTraceElement;

/**
 *
 * @author sk-xinyilong
 */
public class Athrow extends NoOperandByteCode {

    @Override
    public void execute(Frame frame) {
        Jobject ex = frame.getOperandStack().popRef();
        if (ex == null) {
            throw new NullPointerException();
        }
        
        Jthread thread = frame.getThread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }

    private boolean findAndGotoExceptionHandler(Jthread thread, Jobject ex) {
        while (true) {
            Frame frame = thread.currentFrame();
            int handlerPc = frame.getMethod().findExceptionHandlerPc(
                    ex.getBelongClass(), frame.getNextPc()-1);
            if (handlerPc > 0) {
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(ex);
                frame.setNextPc(handlerPc);
                return true;
            }
            
            thread.popFrame();
            if (thread.isStackEmpty()) {
                break;
            }
        }
        return false;
    }

    private void handleUncaughtException(Jthread thread, Jobject ex) {
        Jobject jStr = ex.getRefVar("detailMessage", "Ljava/lang/String;");
        if (jStr != null) {
            String str = Jstring.internObjectToString(jStr);
            System.err.println(ex.getBelongClass().getClassName().replace("/", ".") + ": " + str);
        } else {
            System.err.println(ex.getBelongClass().getClassName().replace("/", ".") + ": ");
        }
        
        StackTraceElement[] stes = (StackTraceElement[])ex.getArray();
        for (StackTraceElement ste : stes) {
            System.err.println("\tat " + ste.toString());
        }
    }
    
}
