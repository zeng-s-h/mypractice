package com.example.loginsession.aop;

import com.example.loginsession.dynamicdatasource.DataSourceContextHolder;
import com.example.loginsession.dynamicdatasource.DataSourceSelector;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 小白i
 * @date 2020/7/24
 */
@Slf4j
@Aspect
@Order(value = 1)
@Component
public class DataSourceContextAop {
    @Around("@annotation(com.example.loginsession.dynamicdatasource.DataSourceSelector)")
    public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
        boolean clear = true;
        try {
            Method method = this.getMethod(pjp);
            DataSourceSelector dataSourceImport = method.getAnnotation(DataSourceSelector.class);
            clear = dataSourceImport.clear();
            DataSourceContextHolder.set(dataSourceImport.value().getDataSourceName());
            log.info("========数据源切换至：{}", dataSourceImport.value().getDataSourceName());
            return pjp.proceed();
        } finally {
            if (clear) {
                DataSourceContextHolder.clear();
            }

        }
    }

    /**
     * 获取注解的方法名
     * @param pjp jointPoint
     * @return Method
     */
    private Method getMethod(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        return signature.getMethod();
    }

}
