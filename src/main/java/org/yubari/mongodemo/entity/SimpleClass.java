package org.yubari.mongodemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("simple_class")
@Data
public class SimpleClass implements IKeyedObject {
    @Id
    private String id;
    private String name;

    @Override
    public String key() {
        return getId();
    }
}
