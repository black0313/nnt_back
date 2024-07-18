package com.example.nnt_project.annotations;

import java.lang.annotation.*;



@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    String value();
}
