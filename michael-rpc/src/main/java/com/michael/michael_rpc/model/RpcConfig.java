package com.michael.michael_rpc.model;

import com.michael.michael_rpc.loadBalancer.LoadBalancerKeys;
import com.michael.michael_rpc.serializer.SerializerKeys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Michael
 */
@Data@NoArgsConstructor@AllArgsConstructor
public class RpcConfig {

    private String host="http://localhost";
    private int port=8081;
    private String serializerType= SerializerKeys.JDK;
    private String loadBalancerType= LoadBalancerKeys.ROUND_ROBIN;
    private RegistryConfig registryConfig=new RegistryConfig();
}
