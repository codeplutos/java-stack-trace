package com.c0d3p1ut0s;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class StackTraceTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor visitor = new ClassAdapter(cw, className);
            cr.accept(visitor, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ClassAdapter extends ClassVisitor implements Opcodes {
    String clazzName = null;

    public ClassAdapter(final ClassVisitor cv, String className) {
        super(ASM5, cv);
        this.clazzName = className;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (clazzName != null) {
            final String methodName = clazzName.replace('/', '.') + "." + name;
            for (String methodPrefix : StackTraceHelper.methodPrefix) {
                if (methodName.startsWith(methodPrefix)) {
                    MethodVisitor nmv = new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {
                        @Override
                        protected void onMethodEnter() {
                            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitLdcInsn("Enter Method: " + methodName);
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                            mv.visitTypeInsn(NEW, "java/lang/Exception");
                            mv.visitInsn(DUP);
                            mv.visitLdcInsn("Java Stack Trace");
                            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "(Ljava/lang/String;)V", false);
                            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "(Ljava/io/PrintStream;)V", false);
                        }
                    };
                    return nmv;
                }
            }
        }
        return mv;
    }
}
