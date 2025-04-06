package com.michael.michael_rpc.loadBalancer;

import cn.hutool.core.collection.CollUtil;
import com.michael.michael_rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Michael
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {

    /** 一致性 Hash 环 */
    private final TreeMap<Integer,ServiceMetaInfo> nodes=new TreeMap<>();
    /** 虚拟节点数 */
    private static final int VIRTUAL_NODE_NUM=100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        System.out.println("using consistent-hash load-balancer");
        if(CollUtil.isEmpty(serviceMetaInfoList)){
            return null;
        }

        // 构建一致性哈希环
        for(ServiceMetaInfo serviceMetaInfo:serviceMetaInfoList){
            for(int i=0;i<VIRTUAL_NODE_NUM;i++){
                int key=getHash(serviceMetaInfo.getServiceNodeKey()+"#"+i);
                nodes.put(key,serviceMetaInfo);
            }
        }

        // 根据参数 hash 值得到对应的服务节点
        // 如果不存在大于等于调用请求 hash 值的虚拟节点，则返回环首部的节点
        int hash=getHash(requestParams);
        Map.Entry<Integer, ServiceMetaInfo> entry = nodes.ceilingEntry(hash);
        if(entry==null){
            entry= nodes.firstEntry();
        }
        return entry.getValue();
    }

    private int getHash(Object key){
        return key.hashCode();
    }
}
