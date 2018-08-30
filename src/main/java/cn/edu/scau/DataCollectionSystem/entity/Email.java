package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Email")
@Getter
@Setter
@Accessors(chain = true)
public class Email {

    @Field
    private String address;

    @Field
    private String name;

    @Field
    private boolean enable;

    public Email() {
    }

    public Email(String address, String name, boolean enable) {
        this.address = address;
        this.name = name;
        this.enable = enable;
    }
}
