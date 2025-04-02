package com.michael.consumer;

import com.michael.common.model.User;
import com.michael.common.service.UserService;
import com.michael.michael_rpc.RpcApplication;
import com.michael.michael_rpc.proxy.ServiceProxyFactory;
import com.michael.michael_rpc.registry.Registry;
import com.michael.michael_rpc.registry.RegistryFactory;

/**
 * @author Michael
 */
public class ConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        String name = userService.getName(new User("michael"));
        System.out.println("RESPONSE: "+name);
    }
}
