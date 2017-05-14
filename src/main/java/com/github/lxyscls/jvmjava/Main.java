/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import com.github.lxyscls.jvmjava.classpath.ClassPath;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import java.io.IOException;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author sk-xinyilong
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Cmd cmd;
        try {
            cmd = Cmd.parseCmd(args);
            if (cmd.versionFlag) {
                System.out.println("version 0.0.1");
            } else if (cmd.helpFlag || cmd.runClass == null) {
                Cmd.printUsage();
            } else {
                startJVM(cmd);
            }
        } catch (ParseException ex) {
            Cmd.printUsage();
        }
    }
    
    static void startJVM(Cmd cmd) throws IOException {
        ClassLoader cl = new ClassLoader(new ClassPath(cmd.XjreOption, cmd.cpOption));
        Jclass cls = cl.loadClass(cmd.runClass.replace(".", "/"));
        Method method = cls.getMainMethod();
        if (method != null) {
            Interpreter.interpret(method);
        } else {
            System.out.printf("Main method not found in class %s\n", cls.getClassName());
        }
    }
}
