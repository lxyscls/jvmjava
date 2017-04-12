/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.constant;

import com.github.lxyscls.jvmjava.classfile.ClassReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
class ConstantUtf8Info extends ConstantInfo {
    private final String str;
    
    public ConstantUtf8Info(ClassReader reader) throws IOException {
        str = reader.readUtf8();
    }
    
    public String getUtf8() {
        return str;
    }
}
