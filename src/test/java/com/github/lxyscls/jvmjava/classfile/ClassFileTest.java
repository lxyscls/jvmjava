/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile;

import com.github.lxyscls.jvmjava.classfile.constant.ConstantPool;
import com.github.lxyscls.jvmjava.classpath.Entry;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class ClassFileTest {
    private ClassFile cf;
    
    public ClassFileTest() {
    }
    
    @Before
    public void setUp() throws IOException {
        System.out.println(new File(".\\src\\test\\resources").getCanonicalPath());
        Entry e = Entry.newEntry(".\\src\\test\\resources");
        cf = new ClassFile(new ClassReader(e.readClass("ClassFileTest.class")));
    }

    @Test
    public void testGetMinorVersion() {
        System.out.println("getMinorVersion");
        int expResult = 0;
        int result = cf.getMinorVersion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMajorVersion() {
        System.out.println("getMajorVersion");
        int expResult = 52;
        int result = cf.getMajorVersion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetConstantPool() {
        System.out.println("getConstantPool");
        ConstantPool result = cf.getConstantPool();
        assertNotNull(result);
    }

    @Test
    public void testGetAccessFlags() {
        System.out.println("getAccessFlags");
        int result = cf.getAccessFlags();
        assertTrue((result & 0x0001) == 0x0001);
    }

    @Test
    public void testGetFields() {
        System.out.println("getFields");
        MemberInfo[] result = cf.getFields();
        assertEquals(result.length, 8);
    }

    @Test
    public void testGetMethods() {
        System.out.println("getMethods");
        MemberInfo[] result = cf.getMethods();
        assertEquals(result.length, 2);
    }

    @Test
    public void testGetClassName() {
        System.out.println("getClassName");
        ClassFile instance = null;
        String expResult = "jvmjava/ClassFileTest";
        String result = cf.getClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSuperClassName() {
        System.out.println("getSuperClassName");
        String expResult = "java/lang/Object";
        String result = cf.getSuperClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetInterfaceNames() {
        System.out.println("getInterfaceNames");
        String[] result = cf.getInterfaceNames();
        assertEquals(result.length, 0);
    }
    
}
