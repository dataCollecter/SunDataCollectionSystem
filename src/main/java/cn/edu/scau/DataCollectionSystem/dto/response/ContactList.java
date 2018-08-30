package cn.edu.scau.DataCollectionSystem.dto.response;

import cn.edu.scau.DataCollectionSystem.dto.Convertible;
import cn.edu.scau.DataCollectionSystem.dto.request.Contact;
import cn.edu.scau.DataCollectionSystem.entity.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ContactList implements Convertible<ContactList, Email> {

    List<Contact> result;

    @Override
    public ContactList convertTo(List<Email> emails) {
        if (emails.isEmpty())
            return this;

        List<Contact> results = new ArrayList<>();
        for (Email email : emails)
            results.add(new Contact(email));

        return setResult(results);
    }

}
