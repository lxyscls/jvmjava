/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant;

import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;

/**
 *
 * @author sk-xinyilong
 */
public class MethodLookup {
    public static Method lookupMethodInClass(Jclass cls, String name, String descriptor) {
        for (Jclass _cls = cls; _cls != null; _cls = _cls.getSuperClass()) {
            for (Method method: _cls.getMethods()) {
                if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(Jclass[] interfaceClasses, String name, String descriptor) {
        for (Jclass intf: interfaceClasses) {
            for (Method method: intf.getMethods()) {
                if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                    return method;
                }
            }
            Method method = lookupMethodInInterfaces(intf.getInterfaceClasses(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }    
}
