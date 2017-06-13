/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.classfile.attribute;

import com.github.lxyscls.jvmjava.classfile.ClassReader;

/**
 *
 * @author sk-xinyilong
 */
class LineNumberTableEntry {
    private final int startPc;
    private final int lineNumber;
    
    LineNumberTableEntry(int startPc, int lineNumber) {
        this.startPc = startPc;
        this.lineNumber = lineNumber;
    }
    
    public int getStartPc() {
        return startPc;
    }
    
    public int getLineNumber() {
        return lineNumber;
    }
}
public class LineNumberTableAttributeInfo extends AttributeInfo {
    private final LineNumberTableEntry[] lineNumberTable;

    public LineNumberTableAttributeInfo(ClassReader reader) {
        int lineNumberTableLen = reader.readUint16();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLen];
        for (int i = 0; i < lineNumberTable.length; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }    
    
    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length-1; i >= 0; i--) {
            LineNumberTableEntry e = lineNumberTable[i];
            if (pc >= e.getStartPc()) {
                return e.getLineNumber();
            }
        }
        return -1;
    }
}
