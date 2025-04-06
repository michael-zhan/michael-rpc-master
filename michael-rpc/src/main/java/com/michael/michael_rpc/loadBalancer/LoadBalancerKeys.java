package com.michael.michael_rpc.loadBalancer;

/**
 * @author Michael
 */
public interface LoadBalancerKeys {
    String ROUND_ROBIN="roundrobin";
    String WEIGHTED_ROUND_ROBIN="weightedroundrobin";
    String RANDOM="random";
    String CONSISTENT_HASH="consistenthash";
}
