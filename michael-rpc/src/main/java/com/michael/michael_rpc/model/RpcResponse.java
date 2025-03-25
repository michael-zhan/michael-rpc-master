package com.michael.michael_rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Michael
 */
@Data@Builder@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {

    private Class<?> dataType;
    private Object data;
    private String message;
    private Exception exception;
}
