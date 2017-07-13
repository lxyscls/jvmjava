/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.java.lang;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author sk-xinyilong
 */
public class NclassLoader {
    private NclassLoader() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/ClassLoader", "registerNatives", "()V", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {     
                }
            }
        ); 
        NativeMethod.registerNativeMethod("java/lang/ClassLoader", "findBuiltinLib", "(Ljava/lang/String;)Ljava/lang/String;", 
            new NativeMethod() {
                @Override
                public void func(Frame frame) {    
                    Jobject jstr = frame.getLocalVars().getRef(0);
                    String name = Jstring.internObjectToString(jstr);                    
                    try {
                        Method findBuiltinLib = ClassLoader.class.getDeclaredMethod("findBuiltinLib", new Class[]{String.class});
                        findBuiltinLib.setAccessible(true);
                        frame.getOperandStack().pushRef(
                                Jstring.stringToInternObject(frame.getMethod().getBelongClass().getClassLoader(), 
                                        (String)findBuiltinLib.invoke(null, name)
                                )
                        );
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.err.println(ex);
                        System.exit(-1);
                    }
                }
            }
        );        
    }
}
