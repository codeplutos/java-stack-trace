package com.c0d3p1ut0s;


import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;


public class JavaStackTrace {
    public static void premain(String agentArgs, Instrumentation inst) throws NoSuchFieldException, IllegalAccessException, UnmodifiableClassException
    {
        if(agentArgs==null){
            StackTraceHelper.printUsage();
            return;
        }
        String[] agentArgArray=agentArgs.split(":");
        if(agentArgArray.length!=2){
            StackTraceHelper.printUsage();
            return;
        }
        if(agentArgArray[0].equals(StackTraceHelper.METHOD)){
            String methodPrefix=agentArgArray[1].replace(".","/");
            StackTraceHelper.methodPrefix.add(methodPrefix);
        }else {
            if (!agentArgArray[0].equals(StackTraceHelper.FILE) || !StackTraceHelper.loadMethodFile(agentArgArray[1])) {
                StackTraceHelper.printUsage();
                return;
            }
        }

        System.out.println("JavaStackTrace start now...");
        inst.addTransformer(new StackTraceTransformer());
    }
}
