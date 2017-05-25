/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classpath.ClassPath;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class JclassTest {
    
    public JclassTest() {
    }

    @Test
    public void testGetClassName() throws IOException {
        System.out.println("getClassName");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));        
        Jclass instance = cl.loadClass("java/lang/String");
        String expResult = "java/lang/String";
        String result = instance.getClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetClassLoader() throws IOException {
        System.out.println("getClassLoader");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));          
        Jclass instance = cl.loadClass("java/lang/String");
        ClassLoader expResult = cl;
        ClassLoader result = instance.getClassLoader();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSuperClassName() throws IOException {
        System.out.println("getSuperClassName");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));         
        Jclass instance = cl.loadClass("java/lang/String");
        String expResult = "java/lang/Object";
        String result = instance.getSuperClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSuperClass() throws IOException {
        System.out.println("getSuperClass");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));         
        Jclass instance = cl.loadClass("java/lang/String");
        Jclass expResult = cl.loadClass("java/lang/Object");
        Jclass result = instance.getSuperClass();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetInterfaceNames() throws IOException {
        System.out.println("getInterfaceNames");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));         
        Jclass instance = cl.loadClass("java/lang/String");
        String[] expResult = {"java/io/Serializable", "java/lang/Comparable", "java/lang/CharSequence"};
        String[] result = instance.getInterfaceNames();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testGetInterfaceClasses() throws IOException {
        System.out.println("getInterfaceClasses");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));          
        Jclass instance = cl.loadClass("java/lang/String");
        Jclass[] expResult = {cl.loadClass("java/io/Serializable"), 
            cl.loadClass("java/lang/Comparable"), cl.loadClass("java/lang/CharSequence")};
        Jclass[] result = instance.getInterfaceClasses();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testIsSubclassOf() throws IOException {
        System.out.println("isSubclassOf");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));          
        Jclass cls = cl.loadClass("java/lang/Object");
        Jclass instance = cl.loadClass("java/lang/String");
        boolean expResult = true;
        boolean result = instance.isSubClassOf(cls);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsAssignableFrom() throws IOException {
        System.out.println("isAssignableFrom");
        ClassLoader cl = new ClassLoader(
                new ClassPath("C:\\Program Files\\Java\\jdk1.8.0_91\\jre", ""));         
        Jclass cls = cl.loadClass("java/lang/String");
        Jclass instance = cl.loadClass("java/io/Serializable");
        boolean expResult = true;
        boolean result = instance.isAssignableFrom(cls);
        assertEquals(expResult, result);
    }
    
}
