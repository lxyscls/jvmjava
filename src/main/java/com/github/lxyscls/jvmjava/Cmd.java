/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lxyscls.jvmjava;

import java.util.Arrays;
import org.apache.commons.cli.*;

/**
 *
 * @author sk-xinyilong
 */
class Cmd {    
    boolean helpFlag;
    boolean versionFlag;
    String cpOption;
    String runClass;
    String[] runClassArgs;
    
    private Cmd() {}
    
    static void printUsage() {
        System.out.println("Usage: Main [-options] class [args...]");
    }
    
    static Cmd parseCmd(String[] args) {
        Cmd cmd = new Cmd();
        
        Options options = new Options();        
        options.addOption("help", false, "print help message");
        options.addOption("?", false, "print help message");
        options.addOption("version", false, "print version and exit");
        options.addOption("classpath", true, "classpath");
        options.addOption("cp", true, "classpath");
        
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cl = parser.parse(options, args);
            cmd.helpFlag = cl.hasOption("help") || cl.hasOption("?");
            cmd.versionFlag = cl.hasOption("version");
            if (cl.hasOption("classpath")) {
                cmd.cpOption = cl.getOptionValue("classpath");
            } else if (cl.hasOption("cp")) {
                cmd.cpOption = cl.getOptionValue("cp");
            }
            
            String[] leftOverArgs = cl.getArgs();
            if (leftOverArgs.length > 0) {
                cmd.runClass = leftOverArgs[0];
                cmd.runClassArgs = Arrays.copyOfRange(leftOverArgs, 1, leftOverArgs.length);
            }
        } catch (ParseException e) {
            printUsage();
        }        
        return cmd;
    }
}
