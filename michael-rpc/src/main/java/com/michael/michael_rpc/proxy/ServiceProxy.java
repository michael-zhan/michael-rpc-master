package com.michael.michael_rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.michael.michael_rpc.model.RpcRequest;
import com.michael.michael_rpc.model.RpcResponse;
import com.michael.michael_rpc.serializer.JdkSerializer;
import com.michael.michael_rpc.serializer.Serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Michael
 */
public class ServiceProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();
        // 构建 rpcRequest 并序列化
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsTypeList(method.getParameterTypes())
                .paramsList(args).build();
        byte[] bytes = serializer.serialize(rpcRequest);

        // 发送请求并接收返回
        HttpResponse response = HttpRequest.post("http://localhost:8080").body(bytes).execute();
        // 反序列化
        RpcResponse rpcResponse = serializer.deserialize(response.bodyBytes(), RpcResponse.class);
        return rpcResponse.getData();
    }
}
