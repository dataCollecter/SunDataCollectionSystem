package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class User {

    @Id
    private String userid;

    private String password;

    public User() {
    }

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}
