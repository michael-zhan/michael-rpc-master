package com.michael.michael_rpc;

import com.michael.michael_rpc.constant.RpcConstant;
import com.michael.michael_rpc.model.RegistryConfig;
import com.michael.michael_rpc.model.RpcConfig;
import com.michael.michael_rpc.registry.Registry;
import com.michael.michael_rpc.registry.RegistryFactory;
import com.michael.michael_rpc.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Michael
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 初始化
     */
    public static void init(){
        RpcConfig loadRpcConfig=null;
        RegistryConfig registryConfig=null;
        try {
            loadRpcConfig = ConfigUtil.loadConfig(RpcConfig.class, RpcConstant.CONFIG_PREFIX);
            rpcConfig=loadRpcConfig;
            registryConfig = ConfigUtil.loadConfig(RegistryConfig.class, RpcConstant.REGISTRY_PREFIX);
            rpcConfig.setRegistryConfig(registryConfig);
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistryType());
            registry.init(registryConfig);
        }catch (Exception e){
            rpcConfig=new RpcConfig();
        }
    }

    /**
     * 双重校验锁保证 RpcConfig 单例
     * @return
     */
    public static RpcConfig getRpcConfig(){
        if(rpcConfig==null){
            synchronized (RpcApplication.class){
                if(rpcConfig==null){
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
