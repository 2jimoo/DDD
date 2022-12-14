package com.example.ddd.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class EntityCommonInfoInjector {

    //[방법] BCI(Byte Code Injection)
    // 1. 바이트 코드 조작. AspectJ나 Spring의 AOP에서 등장하는 개념.
    //    (ex.lombok 내부 - AsmUtil, delombok....)
    //    실행 시간 중 .java파일을 읽어서 변경한다. -> 컴파일러 수준의 작성이 필요하다.
    //    실행 시간 중 .class파일을 읽어서 변경한다. ->기존의 클래스에 코드(파일,데이터)를 덧붙여서 변경할 수 있게 된다.
    // 2. javac가 컴파일한 AST에서 조작한 클래스를 넣고 최종 AST로 바이트코드 생성

    //[task]
    //1. public으로 field만 넣어보기
    //2. private field로 public getter/setter method 넣어보기
    //3. private field, 접근 레벨,getter/setter 지정받아 method 넣기

    public void addFields(Class<?> clazz) throws IOException {
        // ClassReader - 외부의 .class를 읽어오는 클래스이다.
        ClassReader classReader = new ClassReader(clazz.getName());

        // ClassWriter - .class를 쓴다.
        // 파일로 바로 작성해주는 건 없고 바이트 배열로 변형시켜주는데 이를 이용해서 추후에 FileOutputStream일 이용해서 쓰게된다.
        // COMPUTE_MAXS vs COMPUTE_FRAMES : 스택프레임을 계산하느냐 안하느냐
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //ClassAdapter - .class파일을 변경하기 위해 작성해야하는 추상클래스. 3.3.1버전 이후로는 Adapter가 Visitor에 포함됬다.
        ClassAdapter classAdapter = new EntityCommonInfoAdapter(classWriter);
        classReader.accept(classAdapter, ClassReader.SKIP_FRAMES);

       try(FileOutputStream stream = new FileOutputStream(clazz.getName()+".class")){
           stream.write(classWriter.toByteArray());
       }
    }

}
