/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.runtimedata;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author sk-xinyilong
 */
public class Thread {
    private int pc;
    private final Deque<Frame> stack;

    public Thread() {
        this.stack = new ArrayDeque<>();
    }
    
    void pushFrame(Frame frame) {
        frame.lower = stack.peekFirst();
        stack.addFirst(frame);
    }
    
    Frame popFrame() {
        Frame frame = stack.pollFirst();
        frame.lower = null;
        return frame;
    }
    
    Frame currentFrame() {
        return stack.peekFirst();
    }
}
