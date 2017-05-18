/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classpath.ClassPath;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class ClassLoaderTest {
    
    public ClassLoaderTest() {
    }

    @Test
    public void testLoadClass() throws Exception {
        System.out.println("loadClass");
        ClassLoader instance = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));
        Jclass expResult = instance.loadClass("java/lang/String");
        Jclass result = instance.loadClass("java/lang/String");
        assertEquals(expResult, result);
    }
    
}
