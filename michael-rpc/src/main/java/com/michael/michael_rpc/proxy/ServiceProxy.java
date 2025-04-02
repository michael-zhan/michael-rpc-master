package com.michael.michael_rpc.proxy;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.michael.michael_rpc.RpcApplication;
import com.michael.michael_rpc.model.*;
import com.michael.michael_rpc.registry.Registry;
import com.michael.michael_rpc.registry.RegistryFactory;
import com.michael.michael_rpc.serializer.JdkSerializer;
import com.michael.michael_rpc.serializer.Serializer;
import com.michael.michael_rpc.serializer.SerializerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Michael
 */
public class ServiceProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 读取配置文件
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 读取配置中心
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistryType());
        List<ServiceMetaInfo> serviceMetaInfos = registry.serviceDiscovery("myService");
        if(serviceMetaInfos==null){
            throw new RuntimeException("No available service");
        }
        // 暂时固定取第一个服务节点
        ServiceMetaInfo serviceMetaInfo = serviceMetaInfos.get(0);
        // 指定序列化器
        String serializerType = rpcConfig.getSerializerType();
        Serializer serializer= SerializerFactory.getInstance(serializerType);
        // 构建 rpcRequest 并序列化
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsTypeList(method.getParameterTypes())
                .paramsList(args).build();
        byte[] bytes = serializer.serialize(rpcRequest);

        // 发送请求并接收返回
        HttpResponse response = HttpRequest.post(String.format("%s:%s", serviceMetaInfo.getServiceHost(),serviceMetaInfo.getServicePort())).body(bytes).execute();
        // 反序列化
        RpcResponse rpcResponse = serializer.deserialize(response.bodyBytes(), RpcResponse.class);
        return rpcResponse.getData();
    }
}
