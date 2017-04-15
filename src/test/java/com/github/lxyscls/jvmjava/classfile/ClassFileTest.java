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
        Entry e = Entry.newEntry(".\\src\\test\\resources");
        cf = new ClassFile(new ClassReader(e.readClass("ClassFileTest.class")));
    }

    @Test
    public void testGetMinorVersion() {
        int expResult = 0;
        int result = cf.getMinorVersion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMajorVersion() {
        int expResult = 52;
        int result = cf.getMajorVersion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetConstantPool() {
        ConstantPool result = cf.getConstantPool();
        assertNotNull(result);
    }

    @Test
    public void testGetAccessFlags() {
        int result = cf.getAccessFlags();
        assertTrue((result & 0x0001) == 0x0001);
    }

    @Test
    public void testGetFields() {
        MemberInfo[] result = cf.getFields();
        assertEquals(result.length, 8);
    }

    @Test
    public void testGetMethods() {
        MemberInfo[] result = cf.getMethods();
        assertEquals(result.length, 2);
    }

    @Test
    public void testGetClassName() {
        ClassFile instance = null;
        String expResult = "jvmjava/ClassFileTest";
        String result = cf.getClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSuperClassName() {
        String expResult = "java/lang/Object";
        String result = cf.getSuperClassName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetInterfaceNames() {
        String[] result = cf.getInterfaceNames();
        assertEquals(result.length, 0);
    }
    
}
