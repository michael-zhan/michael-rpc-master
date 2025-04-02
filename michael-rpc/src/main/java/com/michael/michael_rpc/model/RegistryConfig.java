package com.michael.michael_rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Michael
 */
@Data
@NoArgsConstructor@AllArgsConstructor
public class RegistryConfig implements Serializable {

    private String registryType="etcd";
    private String registryAddress;
    private String registryUsername;
    private String registryPassword;
    private Long registryTimeout=10000L;

}
