package com.c0d3p1ut0s;


import java.util.ArrayList;

public class StackTraceHelper {
    public static final String METHOD="m";
    public static final String FILE="f";

    public static ArrayList<String> methodPrefix=new ArrayList<String>();

    public static void enterMethod(StackTraceElement[] stackTraceElements){
        System.out.println("JavaStackTrace: ");
        for(StackTraceElement stackTraceElement:stackTraceElements){
            System.out.println("\tat "+stackTraceElement);
        }
        System.out.println("");
    }

    public static void printUsage(){
        System.out.println("Usage: ");
    }

    public static boolean loadMethodFile(String fileName){
        return true;
    }
}
