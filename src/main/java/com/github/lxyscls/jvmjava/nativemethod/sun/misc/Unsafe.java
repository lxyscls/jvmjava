/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava.nativemethod.sun.misc;

import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import java.lang.reflect.Field;


/**
 *
 * @author sk-xinyilong
 */
public class Unsafe {
    private Unsafe() {}
    
    public static void init() {
        NativeMethod.registerNativeMethod("sun/misc/Unsafe", "registerNatives", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "arrayBaseOffset", "(Ljava/lang/Class;)I",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(0);
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "arrayIndexScale", "(Ljava/lang/Class;)I",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(1);
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "addressSize", "()I",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushInt(8);
                            }
                        }
                    );    
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "objectFieldOffset", "(Ljava/lang/reflect/Field;)J",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject field = frame.getLocalVars().getRef(1);
                                int offset = (Integer)field.getRefVar("slot", "I");
                                frame.getOperandStack().pushLong((long)offset);
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "compareAndSwapObject", "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(1);
                                long offset = frame.getLocalVars().getLong(2);
                                Jobject expected = frame.getLocalVars().getRef(4);
                                Jobject nobj = frame.getLocalVars().getRef(5);
                                if (obj.getArray() == null) {
                                    Object[] fields = obj.getFields();
                                    if (fields[(int)offset] == expected) {
                                        fields[(int)offset] = nobj;
                                        frame.getOperandStack().pushInt(1);
                                    } else {
                                        frame.getOperandStack().pushInt(0);
                                    }
                                } else { // array object
                                    Object[] array = obj.getArray();
                                    if (array[(int)offset] == expected) {
                                        array[(int)offset] = nobj;
                                        frame.getOperandStack().pushInt(1);
                                    } else {
                                        frame.getOperandStack().pushInt(0);
                                    }                                        
                                }
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "compareAndSwapInt", "(Ljava/lang/Object;JII)Z",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(1);
                                long offset = frame.getLocalVars().getLong(2);
                                int expected = frame.getLocalVars().getInt(4);
                                int nobj = frame.getLocalVars().getInt(5);
                                if (obj.getArray() == null) {
                                    Object[] fields = obj.getFields();
                                    if ((Integer)fields[(int)offset] == expected) {
                                        fields[(int)offset] = nobj;
                                        frame.getOperandStack().pushInt(1);
                                    } else {
                                        frame.getOperandStack().pushInt(0);
                                    }
                                } else { // array object
                                    Object[] array = obj.getArray();
                                    if ((Integer)array[(int)offset] == expected) {
                                        array[(int)offset] = nobj;
                                        frame.getOperandStack().pushInt(1);
                                    } else {
                                        frame.getOperandStack().pushInt(0);
                                    }                                        
                                }
                            }
                        }
                    );                    
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "getIntVolatile", "(Ljava/lang/Object;J)I",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject obj = frame.getLocalVars().getRef(1);
                                long offset = frame.getLocalVars().getLong(2);
                                if (obj.getArray() == null) {
                                    frame.getOperandStack().pushInt((Integer)obj.getFields()[(int)offset]);
                                } else {
                                    frame.getOperandStack().pushInt((Integer)obj.getArray()[(int)offset]);
                                }
                            }
                        }
                    ); 
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "allocateMemory", "(J)J",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                long offset = frame.getLocalVars().getLong(1);
                                try {
                                    Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                                    f.setAccessible(true);
                                    sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
                                    
                                    frame.getOperandStack().pushLong(unsafe.allocateMemory(offset));
                                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    ); 
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "freeMemory", "(J)V",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                long address = frame.getLocalVars().getLong(1);
                                try {
                                    Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                                    f.setAccessible(true);
                                    sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
                                    
                                    unsafe.freeMemory(address);
                                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    );                    
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "putLong", "(JJ)V",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                long address = frame.getLocalVars().getLong(1);
                                long x = frame.getLocalVars().getLong(3);
                                try {
                                    Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                                    f.setAccessible(true);
                                    sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
                                    
                                    unsafe.putLong(address, x);
                                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    );   
                    NativeMethod.registerNativeMethod("sun/misc/Unsafe", "getByte", "(J)B",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                long address = frame.getLocalVars().getLong(1);
                                try {
                                    Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                                    f.setAccessible(true);
                                    sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
                                    
                                    frame.getOperandStack().pushInt((int)unsafe.getByte(address));
                                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                                    System.err.println(ex);
                                    System.exit(-1);
                                }
                            }
                        }
                    );                    
                }
            }
        );      
    }  
}
