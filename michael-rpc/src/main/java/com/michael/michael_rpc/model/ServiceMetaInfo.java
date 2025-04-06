package com.michael.michael_rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Michael
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class ServiceMetaInfo implements Serializable {

    private String serviceName;
    private String serviceHost;
    private int servicePort;
    private long timeout;

    public String getServiceNodeKey(){
        return String.format("%s/%s:%s",serviceName,serviceHost,servicePort);
    }
}
