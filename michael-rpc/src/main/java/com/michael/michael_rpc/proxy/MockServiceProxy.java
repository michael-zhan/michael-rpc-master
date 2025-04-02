package com.michael.michael_rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MockServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        // 基本数据类型
        if(returnType.isPrimitive()){
            if(returnType == int.class){
                return 1;
            }else if(returnType == boolean.class){
                return true;
            }
        }
        return null;
    }
}
