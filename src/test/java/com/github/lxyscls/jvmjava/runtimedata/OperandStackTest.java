/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sk-xinyilong
 */
public class OperandStackTest {
    OperandStack ops;
    
    public OperandStackTest() {
        ops = new OperandStack(100);
    }

    @Test
    public void testSomeMethod() {
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushLong(2997924580L);
        ops.pushLong(-2997924580L);
        ops.pushFloat(3.1415926F);
        ops.pushDouble(2.71828182845);
        ops.pushRef(null);
        
        assertEquals(ops.popRef(), null);
        assertEquals(ops.popDouble(), 2.71828182845, 0.000001);
        assertEquals(ops.popFloat(), 3.1415926F, 0.000001);
        assertEquals(ops.popLong(),  -2997924580L);
        assertEquals(ops.popLong(),  2997924580L);
        assertEquals(ops.popInt(), -100);
        assertEquals(ops.popInt(), 100);
    }
    
}
