package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "User")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Field
    private String userid;

    @Field
    private String password;

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}
