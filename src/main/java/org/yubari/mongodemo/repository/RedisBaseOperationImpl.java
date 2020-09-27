package org.yubari.mongodemo.repository;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.yubari.mongodemo.entity.IKeyedObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class RedisBaseOperationImpl<T extends IKeyedObject> implements IRedisBaseOperation<T> {
    protected ReactiveRedisOperations<String, T> redisOperations;
    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public RedisBaseOperationImpl(ReactiveRedisConnectionFactory factory) {
        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) superclass;
            Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
            if (actualTypeArgument instanceof Class) {
                entityClass = (Class<T>) actualTypeArgument;
            }
        }
        assert entityClass != null;
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(entityClass);
        RedisSerializationContext.RedisSerializationContextBuilder<String, T> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, T> context = builder.value(serializer).build();
        redisOperations = new ReactiveRedisTemplate<>(factory, context);
    }

    public Mono<Boolean> set(T entity) {
        return redisOperations.opsForValue().set(entity.key(), entity);
    }

    public Mono<T> get(String key) {
        return redisOperations.opsForValue().get(key);
    }

    public Flux<T> getAll() {
        return redisOperations.keys("*").flatMap(redisOperations.opsForValue()::get);
    }
}
