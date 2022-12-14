package com.example.ddd.utils;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.FieldNode;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EntityCommonInfoAdapter extends ClassAdapter {
    Collection<Field> annotationFields = Arrays.stream(EntityCommonInfo.class.getDeclaredFields()).toList();

    public EntityCommonInfoAdapter(ClassVisitor cv) {
        super(cv);
    }

    //동일한 필드가 있는 경우 넘어간다
    @Override
    public FieldVisitor visitField(
            final int access,
            final String name,
            final String desc,
            final String signature,
            final Object value) {
        //필드마다 방문하는 로직
        List<Field> toDelete =
                annotationFields.stream().filter(e -> ObjectUtils.nullSafeEquals(e.getName(), name)).toList();
        toDelete.forEach(e -> annotationFields.remove(e));
        return cv.visitField(access, name, desc, signature, value);
    }

    // ClassVisitor를 사용하고 visitEnd  메서드를 재정의하여 새 필드를 추가합니다.
    // visitEnd 메소드는 로드된 클래스의 모든 메소드와 필드가 순회된 후에 호출됩니다.
    @Override
    public void visitEnd() {
        for (Field field : annotationFields) {
            //Params:
            // access – the field's access flags (see org.objectweb.asm.Opcodes).
            // name – the field's name. desc – the field's descriptor (see Type).
            // desc – the field's descriptor (see Type).
            // signature – the field's signature.
            // value – the field's initial value.
            String descriptor;
            if (field.getName().endsWith("At")) {
                descriptor = Type.getDescriptor(Instant.class);
            } else {
                descriptor = Type.getDescriptor(String.class);
            }

            FieldNode node = new FieldNode(Opcodes.ACC_PUBLIC, field.getName(), descriptor, field.getName(), null);
            node.accept(cv);
        }
        super.visitEnd();
    }

}