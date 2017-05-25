/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;

/**
 *
 * @author sk-xinyilong
 */
public class LocalVars {
    private final Object[] slots;
    
    public LocalVars(int maxLocals) {
        slots = new Object[maxLocals];
    }
    
    public void setInt(int index, Integer val) {
        slots[index] = val;
    }
    
    public int getInt(int index) {
        return (Integer)slots[index];
    }
    
    public void setFloat(int index, Float val) {
        slots[index] = val;
    }
    
    public float getFloat(int index) {
        return (Float)slots[index];
    }
    
    public void setLong(int index, Long val) {
        slots[index] = val;
    }
    
    public long getLong(int index) {
        return (Long)slots[index];
    }
    
    public void setDouble(int index, Double val) {
        slots[index] = val;
    }
    
    public double getDouble(int index) {
        return (Double)slots[index];
    }
    
    public void setRef(int index, Jobject ref) {
        slots[index] = (Object)ref;
    }
    
    public Jobject getRef(int index) {
        return (Jobject) slots[index];
    }
    
    public void setObject(int index, Object obj) {
        slots[index] = obj;
    }
    
    public Object getObject(int index) {
        return slots[index];
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object slot : slots) {
            sb.append(" {").append(String.valueOf(slot)).append("} ");
        }
        return sb.toString();
    }
}
