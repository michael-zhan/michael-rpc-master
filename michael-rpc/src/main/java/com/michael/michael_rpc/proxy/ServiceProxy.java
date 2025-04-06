package com.michael.michael_rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.michael.michael_rpc.RpcApplication;
import com.michael.michael_rpc.loadBalancer.LoadBalancer;
import com.michael.michael_rpc.loadBalancer.LoadBalancerFactory;
import com.michael.michael_rpc.model.*;
import com.michael.michael_rpc.registry.Registry;
import com.michael.michael_rpc.registry.RegistryFactory;
import com.michael.michael_rpc.serializer.Serializer;
import com.michael.michael_rpc.serializer.SerializerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * @author Michael
 */
public class ServiceProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 读取配置文件
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 读取配置中心，获取服务信息
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistryType());
        List<ServiceMetaInfo> serviceMetaInfos = registry.serviceDiscovery(method.getDeclaringClass().getName());
        if(serviceMetaInfos==null){
            throw new RuntimeException("No available service");
        }
        // 指定序列化器
        String serializerType = rpcConfig.getSerializerType();
        Serializer serializer= SerializerFactory.getInstance(serializerType);
        // 构建 rpcRequest 并序列化
        String serviceName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(methodName)
                .paramsTypeList(parameterTypes)
                .paramsList(args).build();
        byte[] bytes = serializer.serialize(rpcRequest);
        // 通过负载均衡器选择服务节点
        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancerType());
        HashMap<String,Object> requestParams = new HashMap<>();
        requestParams.put("serviceName",serviceName);
        requestParams.put("methodName",methodName);
        requestParams.put("paramsTypeList",parameterTypes);
        requestParams.put("args",args);
        ServiceMetaInfo serviceMetaInfo = loadBalancer.select(requestParams,serviceMetaInfos);

        // 发送请求并接收返回
        HttpResponse response = HttpRequest.post(String.format("%s:%s", serviceMetaInfo.getServiceHost(),serviceMetaInfo.getServicePort())).body(bytes).execute();
        // 反序列化
        RpcResponse rpcResponse = serializer.deserialize(response.bodyBytes(), RpcResponse.class);
        return rpcResponse.getData();
    }
}
