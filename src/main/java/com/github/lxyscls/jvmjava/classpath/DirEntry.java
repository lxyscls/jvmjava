/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author sk-xinyilong
 */
class DirEntry implements Entry {
    private final String absDir;
    
    DirEntry(String path) {
        absDir = new File(path).getAbsolutePath();
    }
    
    @Override
    public byte[] readClass(String className) {
        byte[] ret = null;
        String fileName = absDir + className;
        try {
            ret = new byte[(int)new File(fileName).length()];
            new FileInputStream(fileName).read(ret);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return ret;
    }
    
    @Override
    public String toString() {
        return absDir;
    }
}
