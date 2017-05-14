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
public class ConstantLongInfo extends ConstantInfo {
    private final long val;
    
    ConstantLongInfo(ClassReader reader) {
        val = reader.readUint64().longValue();        
    }
    
    public Long getValue() {
        return val;
    }
}
