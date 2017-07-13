/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;
import com.github.lxyscls.jvmjava.classpath.ClassPath;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.Jthread;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class JVM {
    private final Cmd cmd;
    private final ClassLoader cl;
    private final Jthread mainThread;
    
    public JVM(Cmd cmd) throws IOException {
        this.cmd = cmd;
        cl = new ClassLoader(new ClassPath(cmd.XjreOption, cmd.cpOption));
        mainThread = new Jthread();
    }
    
    public void start() throws IOException {
        initVM();
        execMain();
    }

    private void initVM() throws IOException {
        Jclass vmCls = cl.loadClass("sun/misc/VM");
        
        for (Jclass cls = vmCls; cls != null; cls = cls.getSuperClass()) {
            if (cls.getInitStarted() || cls.isInterface()) {
                break;
            }
            cls.setInitStarted();
            Method clinit = MethodLookup.lookupMethodInClass(cls, "<clinit>", "()V");
            if (clinit != null && clinit.getBelongClass() == cls) {
                Frame frame = new Frame(mainThread, clinit);
                mainThread.pushFrame(frame);
            }            
        }
         Interpreter.interpret(mainThread);
    } 

    private void execMain() throws IOException {
        Jclass cls = cl.loadClass(cmd.runClass.replace(".", "/"));
        Method method = cls.getMainMethod();
        if (method != null) {
            Interpreter.interpret(method, cmd.runClassArgs);
        } else {
            System.out.printf("Main method not found in class %s\n", cmd.runClass);
        }        
    }
}
