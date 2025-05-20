package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Author: admin
 * @Description: sky-take-out
 * @Date: Created in 12:53 2025/5/11
 */
@Aspect
@Component
@Slf4j
public class AutofillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行填充..");
        //获取被拦截类型

        MethodSignature signature =(MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = annotation.value();
        //获取被拦截参数
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length==0){
            return;
        }
        Object objects =args[0];
        LocalDateTime now =LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        //获取准备赋值的数据
        if(operationType ==OperationType.INSERT){
            try {
                Method setCreateTime = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(objects,now);
                setUpdateTime.invoke(objects,now);
                setCreateUser.invoke(objects,currentId);
                setUpdateUser.invoke(objects,currentId);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


        else if(operationType == OperationType.UPDATE){
            try {
                Method setUpdateTime = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = objects.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(objects,now);
                setUpdateUser.invoke(objects,currentId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //根据不同操作类型来赋值

    }

}
