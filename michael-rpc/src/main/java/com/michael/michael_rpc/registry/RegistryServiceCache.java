package com.michael.michael_rpc.registry;

import com.michael.michael_rpc.model.ServiceMetaInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    private Map<String, List<ServiceMetaInfo>> registryServiceCache=new HashMap<>();

    /**
     * 写缓存
     * @param cacheKey
     * @param serviceMetaInfoList
     */
    public void writeCache(String cacheKey,List<ServiceMetaInfo> serviceMetaInfoList){
        if(registryServiceCache.containsKey(cacheKey)){
            List<ServiceMetaInfo> serviceMetaInfos = registryServiceCache.get(cacheKey);
        }
    }

    /**
     * 读缓存
     * @param cacheKey
     */
    public void readCache(String cacheKey){

    }

    /**
     * 清除缓存
     * @param serviceNodeKey
     */
    public void clearCache(String serviceNodeKey){

    }
}
