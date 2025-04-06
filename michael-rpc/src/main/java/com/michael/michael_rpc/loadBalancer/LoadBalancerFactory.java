package com.michael.michael_rpc.loadBalancer;

import com.michael.michael_rpc.spi.SpiLoader;

/**
 * @author Michael
 */
public class LoadBalancerFactory {

    static{
        SpiLoader.load(LoadBalancer.class);
    }

    private static final LoadBalancer DEFAULT_LOAD_BALANCER=getInstance(LoadBalancerKeys.ROUND_ROBIN);

    public static LoadBalancer getInstance(String key){
        LoadBalancer loadBalancer = SpiLoader.getInstance(LoadBalancer.class, key);
        return loadBalancer==null?DEFAULT_LOAD_BALANCER:loadBalancer;
    }
}
