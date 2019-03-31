package com.garvey.property.annotation;

import com.garvey.property.constant.Authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author GarveyWong
 * @date 2019/3/27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeededAuthority {
    Authority[] authorities();
}
