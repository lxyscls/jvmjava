/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

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
                registerRegitsterNatives();
                new JVM(cmd).start();
            }
        } catch (ParseException ex) {
            Cmd.printUsage();
        }
    }
    
    static void registerRegitsterNatives() {
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Nclass.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Nobject.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Nsystem.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Nfloat.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Ndouble.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Nstring.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Throwable.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.Thread.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.NclassLoader.init();
        com.github.lxyscls.jvmjava.nativemethod.java.lang.ClassLoader$NativeLibrary.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.misc.VM.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.misc.Unsafe.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.misc.Signal.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.reflect.Reflection.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.reflect.NativeConstructorAccessorImpl.init();
        com.github.lxyscls.jvmjava.nativemethod.sun.io.Win32ErrorMode.init();
        com.github.lxyscls.jvmjava.nativemethod.java.io.FileOutputStream.init();
        com.github.lxyscls.jvmjava.nativemethod.java.io.FileInputStream.init();
        com.github.lxyscls.jvmjava.nativemethod.java.io.FileDescriptor.init();
        com.github.lxyscls.jvmjava.nativemethod.java.io.WinNTFileSystem.init();
        com.github.lxyscls.jvmjava.nativemethod.java.security.AccessController.init();
        com.github.lxyscls.jvmjava.nativemethod.java.util.concurrent.atomic.AtomicLong.init();
    }
}
