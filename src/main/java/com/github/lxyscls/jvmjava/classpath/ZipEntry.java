/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;

/**
 *
 * @author sk-xinyilong
 */
class ZipEntry implements Entry {
    private final String absPath;
    
    ZipEntry(String path) {
        absPath = new File(path).getAbsolutePath();
    }
    
    @Override
    public byte[] readClass(String className) {
        byte[] ret = null;
        try {
            ZipFile zf = new ZipFile(absPath);
            for (Enumeration<? extends java.util.zip.ZipEntry> ez = zf.entries(); ez.hasMoreElements();) {
                java.util.zip.ZipEntry ze = ez.nextElement();
                if (className.equals(ze.toString())) {
                    ret = new byte[(int)ze.getSize()];
                    zf.getInputStream(ze).read(ret);
                    return ret;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return ret;
    }
    
    @Override
    public String toString() {
        return absPath;
    }    
}
