/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

import com.github.lxyscls.jvmjava.classfile.attribute.ExceptionTableEntry;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ClassRef;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.ConstantPool;
import java.io.IOException;

/**
 *
 * @author sk-xinyilong
 */
public class ExceptionTable {
    private final ExceptionHandler[] table;

    ExceptionTable(ExceptionTableEntry[] eTable, ConstantPool cp) {
        table = new ExceptionHandler[eTable.length];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ExceptionHandler(eTable[i].getStartPc(), 
                    eTable[i].getEndPc(), eTable[i].getHandlePc(), 
                    (ClassRef)cp.getConst(eTable[i].getCatchType()));
        }
    }

    public ExceptionHandler findExceptionHandler(Jclass extCls, int pc) throws IOException, IllegalAccessException {
        for (ExceptionHandler eh : table) {
            if (pc >= eh.getStartPc() && pc < eh.getEndPc()) {
                if (eh.getCatchType() == null) { // catch all
                    return eh;
                }
                Jclass catchCls = eh.getCatchType().resolvedClass();
                if (catchCls == extCls || catchCls.isSuperClassof(extCls)) {
                    return eh;
                }
            }
        }
        return null;
    }
    
    public ExceptionHandler[] getExHandlerTable() {
        return this.table;
    }
}
