/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap;

import java.util.HashMap;
import java.util.Map;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class Jstring {
    private Jstring() {}
    
    static Map<String, Jobject> map = new HashMap<>();
    
    public static Jobject stringToInternObject(ClassLoader loader, String str) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        
        char[] ca = str.toCharArray();
        Character[] Ca = new Character[ca.length];
        for (int i = 0; i < Ca.length; i++) {
            Ca[i] = ca[i];
        }
        
        try {
            Jobject jstr = loader.loadClass("java/lang/String").newObject();
            jstr.setRefVar("value", "[C", new Jobject(loader.loadClass("[C"), Ca));
            map.put(str, jstr);
            return jstr;
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(-1);
        }
        return null;
    }
    
    public static String internObjectToString(Jobject obj) {
        Jobject value = obj.getRefVar("value", "[C");
        Character[] Ca = (Character[])value.getArray();
        char[] ca = new char[Ca.length];
        for (int i = 0; i < ca.length; i++) {
            ca[i] = Ca[i];
        }
        return new String(ca);
    }
}
