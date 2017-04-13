/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 *
 * @author sk-xinyilong
 */
class DirEntry extends Entry {
    private final String absDir;
    
    DirEntry(String path) throws IOException {
        absDir = new File(path).getCanonicalPath();
    }
    
    @Override
    public byte[] readClass(String className) throws IOException {
        byte[] ret = null;

        File file = new File(String.join(File.separator, absDir, className));       
        if (file.exists()) {
            ret = new byte[(int)file.length()];
            new FileInputStream(file).read(ret);
        }
        return ret;
    }
    
    @Override
    public String toString() {
        return absDir;
    }
}
