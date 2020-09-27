package org.yubari.mongodemo;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.yubari.mongodemo.entity.SimpleClass;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class MongodemoApplicationTests {
    @Autowired
    protected ReactiveMongoOperations mongoOperations;

    @Test
    void contextLoads() {
    }

    @Test
    void insert(){
        SimpleClass simpleClass = new SimpleClass();
        simpleClass.setName("simple");
        simpleClass.setId(new ObjectId().toString());
        SimpleClass simpleClassSaved = mongoOperations.insert(simpleClass).block();
        System.out.println(simpleClassSaved);
    }

    @Test
    void findAll(){
        List<SimpleClass> simpleClasses = mongoOperations.findAll(SimpleClass.class).collectList().block();
        printAll(simpleClasses);
    }

    private static void printAll(Collection<?> collection){
        collection.forEach(System.out::println);
    }



}
