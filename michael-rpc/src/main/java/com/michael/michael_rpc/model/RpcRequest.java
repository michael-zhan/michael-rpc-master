package com.michael.michael_rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Michael
 */
@Data@Builder@NoArgsConstructor@AllArgsConstructor
public class RpcRequest implements Serializable {

    private String serviceName;
    private String methodName;
    private Class<?>[] paramsTypeList;
    private Object[] paramsList;

}
