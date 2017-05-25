/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata.heap.classfile;

/**
 *
 * @author sk-xinyilong
 */
public class MethodArgParser {
    private final char[] charset;
    private final String descriptor;
    private int offset;
    
    public MethodArgParser(String descriptor) {
        charset = descriptor.toCharArray();
        this.descriptor = descriptor;
        offset = 0;
    }
    
    public MethodArgRet parse() {
        MethodArgRet mar = new MethodArgRet();
        
        startArgs();
        parseArgs(mar);
        endArgs();
        parseRet(mar);
        finish();
        return mar;
    }
    
    private char readChar() {
        return charset[offset++];
    }
    
    private void unReadChar() {
        --offset;
    }

    private void startArgs() {
        if (readChar() != '(') {
            throw new IllegalArgumentException(descriptor);
        }
    }
    
    private void endArgs() {
        if (readChar() != ')') {
            throw new IllegalArgumentException(descriptor);
        }
    }

    private void parseArgs(MethodArgRet mar) {
        while (true) {
            String at = parseArgType();
            if (at.equals("")) {
                break;
            }
            mar.addArgType(at);
        }
    }

    private String parseArgType() {
        switch (readChar()) {
            case 'B': return "B";
            case 'C': return "C";
            case 'D': return "D";
            case 'F': return "F";
            case 'I': return "I";
            case 'J': return "J";
            case 'S': return "S";
            case 'Z': return "Z";
            case 'L': return parseObjectType();
            case '[': return parseArrayType();
            default: unReadChar(); return "";
        }
    }

    private String parseObjectType() {
        int i = offset;
        for (; i < charset.length; i++) {
            if (charset[i] == ';') {
                break;
            }
        }
        if (i == charset.length) {
            throw new IllegalArgumentException(descriptor);
        }
        
        unReadChar(); // 'L'
        int start = offset;
        int end = i+1;
        offset = end;
        return new String(charset, start, end-start);
    }

    private String parseArrayType() {
        int start = offset-1;
        String at = parseArgType();
        if (at.equals("")) {
            throw new IllegalArgumentException(descriptor);           
        }
        return new String(charset, start, offset-start);
    }

    private void parseRet(MethodArgRet mar) {
        if (readChar() == 'V') {
            mar.setRetType("V");
            return;
        }
        unReadChar();
        String rt = parseArgType();
        if (rt.equals("")) {
            throw new IllegalArgumentException(descriptor);              
        }
        mar.setRetType(rt);
    }

    private void finish() {
        if (offset != charset.length) {
            throw new IllegalArgumentException(descriptor);  
        }
    }
}


