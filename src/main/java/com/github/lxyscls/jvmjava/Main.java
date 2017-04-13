/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import com.github.lxyscls.jvmjava.classfile.ClassFile;
import com.github.lxyscls.jvmjava.classfile.ClassReader;
import com.github.lxyscls.jvmjava.classfile.MemberInfo;
import com.github.lxyscls.jvmjava.classpath.Classpath;
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
        Classpath cp = new Classpath(cmd.XjreOption, cmd.cpOption);
        byte[] classData = cp.readClass(cmd.runClass.replace(".", "/"));
        if (classData != null) {
            ClassFile cf = new ClassFile(new ClassReader(classData));
            printClassInfo(cf);
        } else {
            System.out.println("Can't read Class File");
        }
    }
    
    static void printClassInfo(ClassFile cf) {
        System.out.printf("version: %d.%d\n", cf.getMajorVersion(), cf.getMinorVersion());
        System.out.printf("constants count: %d\n", cf.getConstantPool().getPoolSize());
        System.out.printf("access flags: 0x%x\n", cf.getAccessFlags());
        System.out.printf("this class: %s\n", cf.getClassName());
        System.out.printf("super class: %s\n", cf.getSuperClassName());
        System.out.printf("interfaces: ");
        for (String intfName : cf.getInterfaceNames()) {
            System.out.printf(" %s ", intfName);
        }
        System.out.printf("\n");
        System.out.printf("fields count: %d\n", cf.getFields().length);
        for (MemberInfo mInfo : cf.getFields()) {
            System.out.printf(" %s %s\n", mInfo.getName(), mInfo.getDescriptor());
        }
        System.out.printf("methods count: %d\n", cf.getMethods().length);
        for (MemberInfo mInfo : cf.getMethods()) {
            System.out.printf(" %s %s\n", mInfo.getName(), mInfo.getDescriptor());
        }
    }
}
