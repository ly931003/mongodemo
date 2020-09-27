package org.yubari.mongodemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Repository
public class BaseRepository<T> {
    @Autowired
    protected ReactiveMongoOperations reactiveMongoTemplate;
    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseRepository() {
        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) superclass;
            Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
            if (actualTypeArgument instanceof Class) {
                entityClass = (Class<T>) actualTypeArgument;
            }
        }
    }

    public Mono<T> save(T entity) {
        return reactiveMongoTemplate.save(entity);
    }

    public Mono<T> insert(T entity) {
        return reactiveMongoTemplate.insert(entity);
    }
}
