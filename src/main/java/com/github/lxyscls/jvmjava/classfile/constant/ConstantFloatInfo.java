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
public class ConstantFloatInfo extends ConstantInfo {
    private final float val;
    
    ConstantFloatInfo(ClassReader reader) {
        val = Float.intBitsToFloat((int)reader.readUint32());        
    }
    
    public Float getValue() {
        return val;
    }
}
