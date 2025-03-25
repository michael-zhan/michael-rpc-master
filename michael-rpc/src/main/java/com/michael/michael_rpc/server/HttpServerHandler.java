package com.michael.michael_rpc.server;

import com.michael.michael_rpc.model.RpcRequest;
import com.michael.michael_rpc.model.RpcResponse;
import com.michael.michael_rpc.register.LocalRegistry;
import com.michael.michael_rpc.serializer.JdkSerializer;
import com.michael.michael_rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Michael
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();

        // 异步处理 Http Post 请求
        request.bodyHandler(buffer -> {
            byte[] bytes = buffer.getBytes();
            RpcRequest rpcRequest=null;
            try {
                rpcRequest= serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为空，则直接返回
            if(rpcRequest==null){
                rpcResponse.setMessage("RpcRequest is null");
                doResponse(request,rpcResponse,serializer);
            }

            // 获取要调用的服务实现类，并通过反射调用
            try {
                String serviceName = rpcRequest.getServiceName();
                String methodName = rpcRequest.getMethodName();
                Class<?>[] paramsTypeList = rpcRequest.getParamsTypeList();
                Object[] paramsList = rpcRequest.getParamsList();

                Class<?> serviceImpl = LocalRegistry.get(serviceName);
                Method method= serviceImpl.getMethod(methodName,paramsTypeList);
                Object result = method.invoke(serviceImpl.newInstance(), paramsList);
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("OK");
                // 响应
                doResponse(request,rpcResponse,serializer);
            } catch (Exception e) {
                rpcResponse.setException(e);
                e.printStackTrace();
            }

        });
    }

    public void doResponse(HttpServerRequest request,RpcResponse rpcResponse,Serializer serializer){
        HttpServerResponse httpServerResponse = request.response().putHeader("context-type", "application/json");
        try {
            // 序列化
            byte[] bytes = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }

}
