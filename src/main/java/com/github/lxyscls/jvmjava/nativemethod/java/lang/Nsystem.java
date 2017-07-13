package com.github.lxyscls.jvmjava.nativemethod.java.lang;


import com.github.lxyscls.jvmjava.nativemethod.NativeMethod;
import com.github.lxyscls.jvmjava.runtimedata.Frame;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jobject;
import com.github.lxyscls.jvmjava.runtimedata.heap.Jstring;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Field;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Method;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.constant.MethodLookup;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.ClassLoader;
import com.github.lxyscls.jvmjava.runtimedata.heap.classfile.Jclass;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sk-xinyilong
 */
public final class Nsystem {
    private Nsystem() {}
    
    private static final Map<String, String> sprops = new HashMap() {
        {
            put("java.version", System.getProperty("java.version"));
            put("java.vendor", System.getProperty("java.vendor"));
            put("java.vendor.url", System.getProperty("java.vendor.url"));
            put("java.home", System.getProperty("java.home"));
            put("java.class.version", System.getProperty("java.class.version"));
            put("java.class.path", System.getProperty("java.class.path"));
            put("os.name", System.getProperty("os.name"));
            put("os.arch", System.getProperty("os.arch"));
            put("os.version", System.getProperty("os.version"));
            put("file.separator", System.getProperty("file.separator"));
            put("path.separator", System.getProperty("path.separator"));
            put("line.separator", System.getProperty("line.separator"));
            put("user.name", System.getProperty("user.name"));
            put("user.home", System.getProperty("user.home"));
            put("user.dir", System.getProperty("user.dir"));
            put("file.encoding", System.getProperty("file.encoding"));
            put("sun.stdout.encoding", "UTF-8");
            put("sun.stderr.encoding", "UTF-8");
            put("java.library.path", System.getProperty("java.library.path", ""));
            put("sun.boot.library.path", System.getProperty("sun.boot.library.path", ""));
        }
    };
    
    private static boolean checkArrayCopy(Jobject src, Jobject dest) {
        Jclass srcCls = src.getBelongClass();
        Jclass destCls = dest.getBelongClass();
        
        return !(!srcCls.getClassName().startsWith("[") || 
                !destCls.getClassName().startsWith("["));
    }
    
    public static void init() {
        NativeMethod.registerNativeMethod("java/lang/System", "registerNatives", "()V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    NativeMethod.registerNativeMethod("java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                Jobject src = frame.getLocalVars().getRef(0);
                                int srcPos = frame.getLocalVars().getInt(1);
                                Jobject dest = frame.getLocalVars().getRef(2);
                                int destPos = frame.getLocalVars().getInt(3);
                                int length = frame.getLocalVars().getInt(4);
                                
                                if (src == null || dest == null) {
                                    throw new NullPointerException();
                                }
                                if (!checkArrayCopy(src, dest)) {
                                    throw new ArrayStoreException();
                                }
                                if (srcPos < 0 || destPos < 0 || 
                                        srcPos + length > src.getArrayLength() ||
                                        destPos + length > dest.getArrayLength()) {
                                    throw new IndexOutOfBoundsException();
                                }
                                
                                Object[] srcArray = src.getArray();
                                Object[] destArray = dest.getArray();
                                for (int i = 0; i < length; i++) {
                                    destArray[destPos+i] = srcArray[srcPos+i];
                                }
                            }
                        }
                    );
                    NativeMethod.registerNativeMethod("java/lang/System", "currentTimeMillis", "()J",
                        new NativeMethod() {
                            @Override
                            public void func(Frame frame) {
                                frame.getOperandStack().pushLong(System.currentTimeMillis());
                            }
                        }
                    );                    
                }
            }
        );
        NativeMethod.registerNativeMethod("java/lang/System", "initProperties", "(Ljava/util/Properties;)Ljava/util/Properties;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject props = frame.getLocalVars().getRef(0);
                    Method put = MethodLookup.lookupMethodInClass(props.getBelongClass(), "put", 
                            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
                    ClassLoader cl = frame.getMethod().getBelongClass().getClassLoader();
                    for (Map.Entry<String, String> e : sprops.entrySet()) {
                        Frame newFrame = new Frame(frame.getThread(), put);
                        frame.getThread().pushFrame(newFrame);
                        newFrame.getLocalVars().setRef(0, props);
                        newFrame.getLocalVars().setRef(1, Jstring.stringToInternObject(cl, e.getKey()));
                        newFrame.getLocalVars().setRef(2, Jstring.stringToInternObject(cl, e.getValue()));
                    }
                }
            }
        );
        NativeMethod.registerNativeMethod("java/lang/System", "setIn0", "(Ljava/io/InputStream;)V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject in = frame.getLocalVars().getRef(0);
                    Field field = null;
                    for (Field f : frame.getMethod().getBelongClass().getFields()) {
                        if ("in".equals(f.getName()) && "Ljava/io/InputStream;".equals(f.getDescriptor())) {
                            field = f;
                        }
                    }
                    if (field != null) {
                        frame.getMethod().getBelongClass().getStaticVars()[field.getSlotId()] = in;
                    }
                }
            }
        );    
        NativeMethod.registerNativeMethod("java/lang/System", "setOut0", "(Ljava/io/PrintStream;)V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject out = frame.getLocalVars().getRef(0);
                    Field field = null;
                    for (Field f : frame.getMethod().getBelongClass().getFields()) {
                        if ("out".equals(f.getName()) && "Ljava/io/PrintStream;".equals(f.getDescriptor())) {
                            field = f;
                        }
                    }
                    if (field != null) {
                        frame.getMethod().getBelongClass().getStaticVars()[field.getSlotId()] = out;
                    }
                }
            }
        );
        NativeMethod.registerNativeMethod("java/lang/System", "setErr0", "(Ljava/io/PrintStream;)V",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject err = frame.getLocalVars().getRef(0);
                    Field field = null;
                    for (Field f : frame.getMethod().getBelongClass().getFields()) {
                        if ("err".equals(f.getName()) && "Ljava/io/PrintStream;".equals(f.getDescriptor())) {
                            field = f;
                        }
                    }
                    if (field != null) {
                        frame.getMethod().getBelongClass().getStaticVars()[field.getSlotId()] = err;
                    }
                }
            }
        );             
        NativeMethod.registerNativeMethod("java/lang/System", "mapLibraryName", "(Ljava/lang/String;)Ljava/lang/String;",
            new NativeMethod() {
                @Override
                public void func(Frame frame) {
                    Jobject jstr = frame.getLocalVars().getRef(0);
                    String libname = Jstring.internObjectToString(jstr);
                    frame.getOperandStack().pushRef(
                            Jstring.stringToInternObject(frame.getMethod().getBelongClass().getClassLoader(), 
                                    System.mapLibraryName(libname)
                            )
                    );
                }
            }
        );
    }
}
