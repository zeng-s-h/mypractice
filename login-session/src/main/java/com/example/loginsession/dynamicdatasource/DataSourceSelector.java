package com.example.loginsession.dynamicdatasource;

import java.lang.annotation.*;

/**
 * @author 小白i
 * @date 2020/7/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {

    DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;
    boolean clear() default true;
}
