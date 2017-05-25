/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class MethodArgRetTest {
    
    public MethodArgRetTest() {
    }

    @Test
    public void testSomeMethod() {
        MethodArgRet mat = new MethodArgParser("(II)I").parse();
        assertEquals(mat.getArgCount(), 2);
        assertEquals(mat.getRetType(), "I");
        
        mat = new MethodArgParser("(Ljava/lang/String;[Ljava/lang/String;)V").parse();
        assertEquals(mat.getArgCount(), 2);
        assertEquals(mat.getRetType(), "V");
        
        mat = new MethodArgParser("([Ljava/lang/String;[[Ljava/lang/String;)[I").parse();
        assertEquals(mat.getArgCount(), 2);
        assertEquals(mat.getRetType(), "[I");

        mat = new MethodArgParser("([[Ljava/lang/String;[[[Ljava/lang/String;)[[I").parse();
        assertEquals(mat.getArgCount(), 2);
        assertEquals(mat.getRetType(), "[[I");             
    }
    
}
