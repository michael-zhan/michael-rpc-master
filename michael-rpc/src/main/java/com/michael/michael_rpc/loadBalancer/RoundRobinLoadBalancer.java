package com.michael.michael_rpc.loadBalancer;

import cn.hutool.core.collection.CollUtil;
import com.michael.michael_rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Michael
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private AtomicInteger index=new AtomicInteger(0);
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        System.out.println("using round-robin load-balancer");
        if(CollUtil.isEmpty(serviceMetaInfoList)){
            return null;
        }
        int curr=index.getAndIncrement()%serviceMetaInfoList.size();
        return serviceMetaInfoList.get(curr);
    }
}
