package com.michael.michael_rpc.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael
 */
public class LocalRegistry {

    /**
     * 存储注册信息
     */
    private static Map<String,Class<?>> map=new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param serviceName
     * @param serviceImpl
     */
    public static void register(String serviceName,Class<?> serviceImpl){
        map.put(serviceName,serviceImpl);
    }

    /**
     * 获取服务
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }

    /**
     * 删除服务
     * @param serviceName
     */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }

}
