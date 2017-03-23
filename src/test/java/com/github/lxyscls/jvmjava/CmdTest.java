/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class CmdTest {
    public CmdTest() {
    }

    @Test
    public void testSomeMethod() {
        String args1[] = {"-help", "-version"};
        Cmd cmd1 = Cmd.parseCmd(args1);
        assertTrue(cmd1.helpFlag);
        assertTrue(cmd1.versionFlag);
        
        String args2[] = {"-cp", "C:\\Program Files\\Java\\jdk1.8.0_91"};
        Cmd cmd2 = Cmd.parseCmd(args2);
        assertNotNull(cmd2.cpOption);
        
        String args3[] = {"-classpath", "C:\\Program Files\\Java\\jdk1.8.0_91"};
        Cmd cmd3 = Cmd.parseCmd(args3);
        assertNotNull(cmd3.cpOption);
        
        String args4[] = {"myClass", "arg1", "arg2"};
        Cmd cmd4 = Cmd.parseCmd(args4);
        assertEquals("myClass", cmd4.runClass);
        String classArgs1[] = {"arg1", "arg2"};
        assertArrayEquals(classArgs1, cmd4.runClassArgs);
        
        String args5[] = {"-cp", "C:\\Program Files\\Java\\jdk1.8.0_91",
            "myClass", "arg1", "arg2", "arg3"
        };
        Cmd cmd5 = Cmd.parseCmd(args5);
        assertEquals("myClass", cmd5.runClass);
        String classArgs2[] = {"arg1", "arg2", "arg3"};
        assertArrayEquals(classArgs2, cmd5.runClassArgs);        
    }
    
}
