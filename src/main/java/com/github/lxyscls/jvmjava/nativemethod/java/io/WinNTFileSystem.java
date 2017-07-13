/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.io;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class WinNTFileSystem {
    private WinNTFileSystem() {}
    
    public static void init() {    
        NativeMethod.registerNativeMethod("java/io/WinNTFileSystem", "initIDs", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/io/WinNTFileSystem", "getBooleanAttributes", "(Ljava/io/File;)I",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject fileObj = frame.getLocalVars().getRef(1);
                    Jobject pathObj = (Jobject)fileObj.getRefVar("path", "Ljava/lang/String;");
                    File file = new File(Jstring.internObjectToString(pathObj));
                    
                    int attrs = (file.exists() ? 0x01 : 0x00) | 
                            (file.isFile() ? 0x02 : 0x00) |
                            (file.isDirectory() ? 0x04 : 0x00) |
                            (file.isHidden() ? 0x08 : 0x00);
                    frame.getOperandStack().pushInt(attrs);
                }
            }
        );   
        NativeMethod.registerNativeMethod("java/io/WinNTFileSystem", "canonicalize0", "(Ljava/lang/String;)Ljava/lang/String;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject pathObj = frame.getLocalVars().getRef(1);
                    File file = new File(Jstring.internObjectToString(pathObj));
                    
                    try {
                        frame.getOperandStack().pushRef(Jstring.stringToInternObject(
                                frame.getMethod().getBelongClass().getClassLoader(),
                                file.getCanonicalPath())
                        );
                    } catch (IOException ex) {
                        System.err.println(ex);
                        System.exit(-1);
                    }
                }
            }
        );         
    }
}
