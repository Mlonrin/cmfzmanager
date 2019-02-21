package com.baizhi.cmfz.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Target(ElementType.FIELD) 作用范围属性
 * @Retention(RetentionPolicy.RUNTIME) 运行时起效
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelName {
    String name() default "";
}
