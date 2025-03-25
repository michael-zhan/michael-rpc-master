package com.michael.provider;

import com.michael.common.service.UserService;
import com.michael.michael_rpc.register.LocalRegistry;
import com.michael.michael_rpc.server.HttpServer;
import com.michael.michael_rpc.server.VertxHttpServer;

/**
 * @author Michael
 */
public class ProviderExample {

    public static void main(String[] args) {
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        HttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);
    }
}
