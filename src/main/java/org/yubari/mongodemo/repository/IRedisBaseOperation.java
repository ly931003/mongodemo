package org.yubari.mongodemo.repository;

import org.yubari.mongodemo.entity.IKeyedObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRedisBaseOperation<T> {
    Mono<Boolean> set(T t);

    Mono<T> get(String key);

    Flux<T> getAll();
}
