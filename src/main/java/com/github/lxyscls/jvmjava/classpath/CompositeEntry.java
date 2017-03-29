/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.zip.ZipException;

/**
 *
 * @author sk-xinyilong
 */
class CompositeEntry implements Entry {
    private final List<Entry> entryList;
    
    public CompositeEntry(String pathList) {
        entryList = new LinkedList<>();
        for (String path : pathList.split(File.pathSeparator)) {
            entryList.add(Entry.newEntry(path));
        }
    }
    
    @Override
    public byte[] readClass(String className) throws FileNotFoundException, ZipException, IOException {
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
        return String.join(File.pathSeparator, strings);
    }    
}
