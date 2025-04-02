package com.michael.michael_rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Michael
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new ServiceProxy());
    }

    public static <T> T getMockProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new MockServiceProxy());
    }
}
