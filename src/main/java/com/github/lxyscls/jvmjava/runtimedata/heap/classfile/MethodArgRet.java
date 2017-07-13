/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sk-xinyilong
 */
public class MethodArgRet {
    private final List<String> argTypes;
    private String retType;
    
    MethodArgRet() {
        argTypes = new ArrayList<>();
    }
    
    void addArgType(String argType) {
        argTypes.add(argType);
    }
    
    void setRetType(String retType) {
        this.retType = retType;
    }
    
    String getRetType() {
        return this.retType;
    }
    
    String[] getArgTypes() {
        return this.argTypes.toArray(new String[0]);
    }
    
    int getArgCount() {
        return argTypes.size();
    }
}
