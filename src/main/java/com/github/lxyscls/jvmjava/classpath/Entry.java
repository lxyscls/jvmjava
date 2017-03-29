/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

/**
 *
 * @author sk-xinyilong
 */
public interface Entry {
    final String PATHSEPARATOR = System.getProperty("path.separator");
    
    public static Entry newEntry(String path) {
        if (path.contains(PATHSEPARATOR)) {
            return new CompositeEntry(path);
        }        
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }        
        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }       
        return new DirEntry(path);
    }
    
    byte[] readClass(String className);
    @Override
    String toString();
}
