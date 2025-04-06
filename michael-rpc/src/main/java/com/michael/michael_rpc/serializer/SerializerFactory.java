package com.michael.michael_rpc.serializer;

import com.michael.michael_rpc.spi.SpiLoader;

/**
 * @author Michael
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = getInstance(SerializerKeys.JDK);

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        Serializer serializer = SpiLoader.getInstance(Serializer.class, key);
        return serializer==null?DEFAULT_SERIALIZER:serializer;
    }

}