/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import com.github.lxyscls.jvmjava.classpath.Classpath;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipException;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author sk-xinyilong
 */
public class Main {
    public static void main(String[] args) {
        Cmd cmd;
        try {
            cmd = Cmd.parseCmd(args);
            if (cmd.versionFlag) {
                System.out.println("version 0.0.1");
            } else if (cmd.helpFlag || cmd.runClass == null) {
                Cmd.printUsage();
            } else {
                startJVM(cmd);
            }
        } catch (ParseException ex) {
            Cmd.printUsage();
        } catch (FileNotFoundException | ZipException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    static void startJVM(Cmd cmd) throws FileNotFoundException, ZipException, IOException {
        Classpath cp = new Classpath(cmd.XjreOption, cmd.cpOption);
            
        System.out.printf("classpath:%s class:%s ", cp, cmd.runClass);
        System.out.printf("args:");
        for (String arg : cmd.runClassArgs) {
            System.out.printf(" %s ", arg);
        }
        System.out.printf("\n");
            
        byte[] ret = cp.readClass(cmd.runClass.replace(".", "/"));
        System.out.print("class data:");
        System.out.println(Arrays.toString(ret));
    }
}
