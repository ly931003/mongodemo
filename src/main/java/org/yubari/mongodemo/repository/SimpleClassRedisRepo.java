package org.yubari.mongodemo.repository;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.stereotype.Repository;
import org.yubari.mongodemo.entity.SimpleClass;

@Repository
public class SimpleClassRedisRepo extends RedisBaseOperationImpl<SimpleClass> implements ISimpleClassRedisRepo {
    public SimpleClassRedisRepo(ReactiveRedisConnectionFactory factory) {
        super(factory);
    }
}
