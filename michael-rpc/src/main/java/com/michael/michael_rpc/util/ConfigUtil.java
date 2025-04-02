package com.michael.michael_rpc.util;

import cn.hutool.setting.dialect.Props;

/**
 * @author Michael
 */
public class ConfigUtil {

    public static <T> T loadConfig(Class<T> aClass, String prefix){
        Props props = new Props("application.properties");
        return props.toBean(aClass,prefix);
    }
}
