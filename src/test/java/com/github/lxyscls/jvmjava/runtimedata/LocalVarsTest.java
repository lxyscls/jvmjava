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
public class LocalVarsTest {
    LocalVars vars;
    
    public LocalVarsTest() {
        vars = new LocalVars(100);
    }

    @Test
    public void testSomeMethod() {
        vars.setInt(0, 100);
        vars.setInt(1, -100);
        vars.setLong(2, 2997924580L);
        vars.setLong(4, -2997924580L);
        vars.setFloat(6, 3.1415926F);
        vars.setDouble(7, 2.71828182845);
        vars.setRef(9, null);
        
        assertEquals(vars.getInt(0), 100);
        assertEquals(vars.getInt(1), -100);
        assertEquals(vars.getLong(2), 2997924580L);
        assertEquals(vars.getLong(4), -2997924580L);
        assertEquals(vars.getFloat(6), 3.1415926F, 0.000001);
        assertEquals(vars.getDouble(7), 2.71828182845, 0.000001);
        assertNull(vars.getRef(9));
    }
    
}
