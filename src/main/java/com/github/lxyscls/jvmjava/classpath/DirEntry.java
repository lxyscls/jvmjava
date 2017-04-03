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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sk-xinyilong
 */
class DirEntry implements Entry {
    private final String absDir;
    
    DirEntry(String path) {
        String dir = new File(path).getAbsolutePath();
        if (dir.endsWith(".")) {
            dir = dir.substring(0, dir.length()-1);
        }
        absDir = dir;
    }
    
    @Override
    public byte[] readClass(String className) {
        byte[] ret = null;

        File file = new File(String.join(File.separator, absDir, className));       
        if (file.exists()) {
            ret = new byte[(int)file.length()];
            try {
                new FileInputStream(file).read(ret);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DirEntry.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DirEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
    
    @Override
    public String toString() {
        return absDir;
    }
}
