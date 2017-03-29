/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipException;

/**
 *
 * @author sk-xinyilong
 */
public class Classpath {
    private final Entry bootClasspath;
    private final Entry extClasspath;
    private final Entry userClasspath;
    
    public Classpath(String jreOption, String cpOption) throws FileNotFoundException {
        String jreDir = getJreDir(jreOption);
        
        bootClasspath = new WildcardEntry(String.join(File.separator, jreDir, "lib", "*"));
        extClasspath = new WildcardEntry(String.join(File.separator, jreDir, "lib", "*", "ext"));
        
        if (cpOption == null) {
            cpOption = ".";
        }
        userClasspath = Entry.newEntry(cpOption);
    }
    
    public byte[] readClass(String className) throws FileNotFoundException, ZipException, IOException {
        byte[] ret;
        
        ret = bootClasspath.readClass(className + ".class");
        if (ret != null) {
            return ret;
        }
        ret = extClasspath.readClass(className + ".class");
        if (ret != null) {
            return ret;
        }
        ret = userClasspath.readClass(className + ".class");
        return ret;
    }
    
    @Override
    public String toString() {
        return userClasspath.toString();
    }

    private static String getJreDir(String jreOption) throws FileNotFoundException {
        if (jreOption != null && new File(jreOption).exists()) {
            return jreOption;
        }
        if (new File(String.join(File.separator, ".", "jre")).exists()) {
            return String.join(File.separator, ".", "jre");
        }
        Map<String, String> env = System.getenv();
        if (env.containsKey("JAVA_HOME")) {
            return String.join(File.separator, env.get("JAVA_HOME"), "jre");
        }
        throw new FileNotFoundException("JRE not found");
    }
}