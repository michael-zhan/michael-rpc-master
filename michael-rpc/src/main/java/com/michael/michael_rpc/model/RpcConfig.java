package com.michael.michael_rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Michael
 */
@Data@NoArgsConstructor@AllArgsConstructor
public class RpcConfig {

    private String host;
    private int port;
    private String serializerType;
    private RegistryConfig registryConfig=new RegistryConfig();
}
