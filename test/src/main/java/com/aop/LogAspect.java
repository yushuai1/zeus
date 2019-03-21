package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
//    @Pointcut("execution(public * com.controller.*.*(..))")
//    public void param(){
//
//    }

    @Pointcut(value = "@annotation(com.aop.ValParam)")
    public void param() {

    }

    @Before("param()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        String name = Arrays.toString(joinPoint.getArgs());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "param()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("param()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("param()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("param()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");
        try {
            Object o = null;
            Object[] args = pjp.getArgs();
            Map<String,Object> reMap = new HashMap<>();

            for (int i = 0;i<args.length;i++ ){
                Object param = args[i];
                if (param instanceof Integer) {
                    int value = ((Integer) param).intValue();
                    if (value == 0){
                        reMap.put("code",1432);
                    }

                } else if (param instanceof String) {
                    String s = (String) param;
                    if ("".equals(s.trim())){
                        reMap.put("code",1432);
                    }
                } else if (param instanceof Double) {
                    double d = ((Double) param).doubleValue();
                    if (d==0.0d){
                        reMap.put("code",1432);
                    }

                } else if (param instanceof Float) {
                    float f = ((Float) param).floatValue();
                    if (f==0.0f){
                        reMap.put("code",1432);
                    }

                } else if (param instanceof Long) {
                    long l = ((Long) param).longValue();
                    if (l==0L){
                        reMap.put("code",1432);
                    }

                } else if (param instanceof Date) {
                    Date d = (Date) param;
                    if (d==null){
                        reMap.put("code",1432);
                    }
                }
            }
            if (reMap.size()>0&&Integer.parseInt(reMap.get("code").toString()) ==1432){
                o = reMap;
            }else {
                o =  pjp.proceed();
            }
            Signature signature =  pjp.getSignature();
            Class returnType = ((MethodSignature) signature).getReturnType();
            System.out.println("获取返回类型："+returnType);
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
