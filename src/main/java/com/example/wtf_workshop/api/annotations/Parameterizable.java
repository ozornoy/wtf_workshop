package com.example.wtf_workshop.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Поля с этой аннотацией будут заполняться переденным значение,
 * если параметр передан
 */
public @interface Parameterizable {
}
