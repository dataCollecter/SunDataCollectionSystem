package cn.edu.scau.DataCollectionSystem.dto.request;

import cn.edu.scau.DataCollectionSystem.entity.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Contact {

    private String name;

    private String mail;

    public Contact(Email email) {
        this.name = email.getName();
        this.mail = email.getAddress();
    }
}
