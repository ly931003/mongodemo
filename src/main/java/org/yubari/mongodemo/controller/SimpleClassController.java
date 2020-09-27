package org.yubari.mongodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yubari.mongodemo.entity.Coffee;
import org.yubari.mongodemo.entity.SimpleClass;
import org.yubari.mongodemo.repository.ISimpleClassRedisRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SimpleClassController {
    @Autowired
    private ISimpleClassRedisRepo simpleClassRedisRepo;

    @GetMapping("/simpleClasses")
    public Flux<SimpleClass> all() {
        return simpleClassRedisRepo.getAll();
    }

    @GetMapping("/simpleClass/{key}")
    public Mono<SimpleClass> get(@PathVariable String key) {
        return simpleClassRedisRepo.get(key);
    }

    @PostMapping("/simpleClass")
    public Mono<Boolean> get(@RequestBody SimpleClass simpleClass) {
        return simpleClassRedisRepo.set(simpleClass);
    }
}
