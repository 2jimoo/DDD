package com.example.ddd.utils;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@AutoService(Processor.class)
public class EntityCommonInfoAnnotationProcessor extends AbstractProcessor {

    private Collection<FieldSpec> fieldSpecs;
    private Messager messager;
    private Filer filer;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        Collection<Field> annotationFields = Arrays.stream(EntityCommonInfo.class.getDeclaredFields()).toList();
        fieldSpecs = new ArrayList<>();

        for (Field field : annotationFields) {
            FieldSpec fieldSpec = FieldSpec.builder(
                    field.getName().endsWith("At") ? Instant.class : String.class,
                    field.getName(),
                    Modifier.PUBLIC
            ).build();
            fieldSpecs.add(fieldSpec);
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(EntityCommonInfo.class.getName()); // method element
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(EntityCommonInfo.class);

        for (Element e : elementsAnnotatedWith) {
            if (e.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "ElementCommonIfo annotation cannot apply to interface.");
            } else {
                TypeElement typeElement = (TypeElement) e;
                ClassName className = ClassName.get(typeElement);

                TypeSpec classInjected = TypeSpec.classBuilder(
                        className.simpleName()
                ).addFields(fieldSpecs).build();
                try {
                    JavaFile.builder(className.packageName(), classInjected).build().writeTo(filer);
                } catch (IOException exception) {
                    messager.printMessage(Diagnostic.Kind.ERROR,
                            "Class %s with ElementCommonIfo annotation cannot apply to interface.".formatted(className.simpleName()));
                }
            }
        }
        return true; //다른 곳 전달하지않음
        // return false;
    }
}
