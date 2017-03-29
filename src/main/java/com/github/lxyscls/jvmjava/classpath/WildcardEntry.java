/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sk-xinyilong
 */
class WildcardEntry implements Entry {
    private final List<Entry> entryList = new LinkedList<>();
    
    WildcardEntry(String path) {
        String baseDirName = path.substring(0, path.length()-1);
        File baseDir = new File(baseDirName);
        if (baseDir.isDirectory()) {
            for (String file : baseDir.list((File dir, String name) 
                    -> name.endsWith(".jar") || name.endsWith(".JAR"))) {
                entryList.add(new ZipEntry(baseDirName + file));
            }
        }
    }
    
    @Override
    public byte[] readClass(String className) {
        byte[] ret;
        for (Entry entry : entryList) {
            ret = entry.readClass(className);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        List<String> strings = new LinkedList<>();
        entryList.forEach((entry) -> {
            strings.add(entry.toString());
        });
        return String.join(PATHSEPARATOR, strings);
    }    
}
