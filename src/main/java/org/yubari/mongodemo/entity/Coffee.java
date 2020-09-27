package org.yubari.mongodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements IKeyedObject {
    private String id;
    private String name;

    @Override
    public String key() {
        return getId();
    }
}
