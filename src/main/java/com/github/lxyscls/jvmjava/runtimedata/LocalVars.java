/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

/**
 *
 * @author sk-xinyilong
 */
class LocalVars {
    private final Object[] slots;
    
    LocalVars(int maxLocals) {
        slots = new Object[maxLocals];
    }
    
    void setInt(int index, Integer val) {
        slots[index] = val;
    }
    
    int getInt(int index) {
        return (Integer)slots[index];
    }
    
    void setFloat(int index, Float val) {
        slots[index] = val;
    }
    
    float getFloat(int index) {
        return (Float)slots[index];
    }
    
    void setLong(int index, Long val) {
        slots[index] = val;
    }
    
    long getLong(int index) {
        return (Long)slots[index];
    }
    
    void setDouble(int index, Double val) {
        slots[index] = val;
    }
    
    double getDouble(int index) {
        return (Double)slots[index];
    }
    
    void setRef(int index, Object ref) {
        slots[index] = ref;
    }
    
    Object getRef(int index) {
        return slots[index];
    }
}
