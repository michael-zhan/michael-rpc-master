package com.michael.michael_rpc.registry;

import java.util.HashMap;
import java.util.Map;

public class RegistryFactory {

    private static final Map<String,Registry> registryCache=new HashMap<>();

    public static Registry getInstance(String registryKey){
        if(registryCache.containsKey(registryKey)){
            return registryCache.get(registryKey);
        }else if(registryKey.equals("etcd")){
            EtcdRegistry etcdRegistry = new EtcdRegistry();
            registryCache.put("etcd",etcdRegistry);
            return etcdRegistry;
        }else{
            // 暂时只实现 Etcd
            throw new RuntimeException("Only support etcd currently");
        }
    }
}
