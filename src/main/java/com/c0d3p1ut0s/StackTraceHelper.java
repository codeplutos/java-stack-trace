package com.c0d3p1ut0s;


import java.io.*;
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
        File file=new File(fileName);
        if(!file.exists()||!file.isFile()){
            System.err.println(fileName+" is not exist or is not file.");
            return false;
        }
        try{
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                methodPrefix.add(line.trim());
            }
            bufferedReader.close();
            read.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
