package com.zhn.webopenapicore.utils.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * Redis使用Gson进行序列化
 * @author zhn
 * @version 1.0
 */
public class GsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final Gson gson;

    private final Class<T> clazz;

    public GsonRedisSerializer(Class<T> clazz) {
        this(clazz, new GsonBuilder().create());
    }

    public GsonRedisSerializer(Class<T> clazz, Gson gson) {
        this.clazz = clazz;
        this.gson = gson;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return gson.toJson(t).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String json = new String(bytes, DEFAULT_CHARSET);
        return gson.fromJson(json, clazz);
    }
}
