/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classpath;

import java.io.FileNotFoundException;
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
    public void testWildcardEntry() {
        Entry result = Entry.newEntry("C:\\Program Files\\Java\\jdk1.8.0_91\\jre\\lib\\*");
        try {
            assertNotNull(result.readClass("java/lang/Object.class"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    @Test (expected = FileNotFoundException.class)
    public void testDirEntry() throws FileNotFoundException, IOException {
        Entry result = Entry.newEntry(".");
        assertNull(result.readClass("java/lang/Object.class"));
    }
}
