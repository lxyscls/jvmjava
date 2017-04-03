/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

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
    public void testWildcardEntry() {
        Entry result = Entry.newEntry("C:\\Program Files\\Java\\jdk1.8.0_73\\jre\\lib\\*");
        assertNotNull(result.readClass("java/lang/Object.class"));
    }
    
    @Test
    public void testDirEntry() {
        Entry result = Entry.newEntry(".");
        assertNotNull(result.readClass("Object.class"));
    }
    
    @Test
    public void testCompositeEntry() {
        Entry result = Entry.newEntry(".;*;E:\\Code\\jvmjava-master\\build\\classes\\main\\com\\github\\lxyscls\\jvmjava");
        assertNotNull(result.readClass("Cmd.class"));
    }
}
