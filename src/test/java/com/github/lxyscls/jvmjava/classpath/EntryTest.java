/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class EntryTest {
    
    public EntryTest() {
    }

    @Test
    public void testWildcardEntry() throws IOException {
        Entry result = Entry.newEntry("C:\\Program Files\\Java\\jdk1.8.0_91\\jre\\lib\\*");
        assertNotNull(result.readClass("java/lang/Object.class"));
    }
    
    @Test
    public void testDirEntry() throws IOException {
        Entry result = Entry.newEntry(".\\src\\test\\resources\\");
        assertNotNull(result.readClass("ClassFileTest.class"));
    }
    
    @Test
    public void testCompositeEntry() throws IOException {
        Entry result = Entry.newEntry(".;*;.\\src\\test\\resources\\");
        assertNotNull(result.readClass("ClassFileTest.class"));
    }
}
