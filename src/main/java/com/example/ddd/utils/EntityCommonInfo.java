package com.example.ddd.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)// source level에서만 필요, runtime시 필요없음
public @interface EntityCommonInfo {
    String createdAt() default "";

    String createdBy() default "";

    String modifiedAt() default "";

    String modifiedBy() default "";

    String registeredAt() default "";

    String registeredBy() default "";

    String requestedAt() default "";

    String requestedBy() default "";
}
