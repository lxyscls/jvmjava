/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import java.util.LinkedList;
import java.util.Deque;

/**
 *
 * @author sk-xinyilong
 */
public class Jthread {
    private int pc; // Current bytecode's pc
    private final Deque<Frame> stack;

    public Jthread() {
        this.stack = new LinkedList<>();
    }
    
    public void pushFrame(Frame frame) {
        frame.lower = stack.peekFirst();
        stack.addFirst(frame);
    }
    
    public Frame popFrame() {
        Frame frame = stack.pollFirst();
        frame.lower = null;
        return frame;
    }
    
    public Frame currentFrame() {
        return stack.peekFirst();
    }
    
    public int getPc() {
       return pc; 
    }
    
    public void setPc(int pc) {
        this.pc = pc;
    }
}
