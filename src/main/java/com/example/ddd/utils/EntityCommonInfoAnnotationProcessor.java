package com.example.ddd.utils;

import com.google.auto.service.AutoService;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import java.io.IOException;
import java.util.Set;


@AutoService(Processor.class)
public class EntityCommonInfoAnnotationProcessor extends AbstractProcessor {
    //소스 코드를 생성하는데 자바의 아주 유용한 라이브러리 Javapoet도 있다.
///https://better-dev.netlify.app/java/2020/09/10/thejava_18/
//lombok에서 쓰던걸로 써보기
    private final EntityCommonInfoInjector entityCommonInfoInjector = new EntityCommonInfoInjector();
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(EntityCommonInfo.class.getName()); // method element
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(EntityCommonInfo.class);
        for(Element e : elementsAnnotatedWith){
            try {
                TypeElement typeElement=(TypeElement)e;
                Class<?> clazz= ClassUtils.forName(typeElement.getSimpleName().toString(),null);
                entityCommonInfoInjector.addFields(clazz);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        return true; // 처리 후 다른곳에 넘겨주고 싶은 경우 false, 여기서만 끝낼거면 true
    }
}
