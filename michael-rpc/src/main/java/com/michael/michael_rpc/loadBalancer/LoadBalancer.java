package com.michael.michael_rpc.loadBalancer;

import com.michael.michael_rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Michael
 */
public interface LoadBalancer {

    /**
     * 根据请求参数，从服务节点列表中选出一个节点
     * @param requestParams 请求参数
     * @param serviceMetaInfoList 服务节点列表
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
