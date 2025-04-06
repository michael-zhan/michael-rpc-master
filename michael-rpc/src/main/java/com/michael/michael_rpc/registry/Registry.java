package com.michael.michael_rpc.registry;

import com.michael.michael_rpc.model.RegistryConfig;
import com.michael.michael_rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Michael
 */
public interface Registry {


    /**
     * 初始化注册中心
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * provider 注册服务
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * provider 注销服务
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * consumer 获取服务
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 注册中心销毁
     */
    void destroy();

    /**
     * 心跳检测
     */
    void heartBeat();

    /**
     * 服务监听
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);
}
