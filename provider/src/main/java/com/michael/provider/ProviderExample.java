package com.michael.provider;

import com.michael.common.service.UserService;
import com.michael.michael_rpc.RpcApplication;
import com.michael.michael_rpc.model.RpcConfig;
import com.michael.michael_rpc.model.ServiceMetaInfo;
import com.michael.michael_rpc.register.LocalRegistry;
import com.michael.michael_rpc.registry.Registry;
import com.michael.michael_rpc.registry.RegistryFactory;
import com.michael.michael_rpc.server.HttpServer;
import com.michael.michael_rpc.server.VertxHttpServer;

/**
 * @author Michael
 */
public class ProviderExample {

    public static void main(String[] args) throws Exception {
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        HttpServer vertxHttpServer = new VertxHttpServer();
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 注册服务信息
        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistryType());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceHost(rpcConfig.getHost());
        serviceMetaInfo.setServicePort(rpcConfig.getPort());
        registry.register(serviceMetaInfo);

        vertxHttpServer.doStart(rpcConfig.getPort());
    }

}
