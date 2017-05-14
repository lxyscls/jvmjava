/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.ClassReader;

/**
 *
 * @author sk-xinyilong
 */
public class ConstantDoubleInfo extends ConstantInfo {
    private final double val;

    ConstantDoubleInfo(ClassReader reader) {
        val = reader.readUint64().doubleValue();
    }    
    
    public Double getValue() {
        return val;
    } 
}
