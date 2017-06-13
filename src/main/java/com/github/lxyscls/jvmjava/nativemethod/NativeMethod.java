/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod;

import com.github.lxyscls.jvmjava.runtimedata.Frame;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sk-xinyilong
 */
public abstract class NativeMethod {
    static Map<String, NativeMethod> map = new HashMap<>();

    public abstract void func(Frame frame);
    
    public static void registerNativeMethod(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        map.put(key, method);
    }
    
    public static NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }
}
