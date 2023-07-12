package com.zhn.webopenapicore.utils.string;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.Result;

import java.util.Map;

/**
 * The type Json util.
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/12 18:32
 * @blog www.zhnblog.icu
 */
public class JSONUtil {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    /**
     * To param map map.
     *
     * @param requestParam the request param
     * @return the map
     */
    public static Map<String, Object> toMap(String requestParam) {
        Map<String, Object> paramMap;
        try {
            // 使用Gson库将JSON字符串转换为Map
            paramMap = gson.fromJson(requestParam,
                    new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.ERROR,"字符串转JSON格式错误",e);
        }
        return paramMap;
    }

}
