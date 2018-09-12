package cn.edu.scau.DataCollectionSystem.controller;

import cn.edu.scau.DataCollectionSystem.dto.request.DeleteInfo;
import cn.edu.scau.DataCollectionSystem.dto.request.Contact;
import cn.edu.scau.DataCollectionSystem.dto.response.BaseResponse;
import cn.edu.scau.DataCollectionSystem.dto.response.ContactList;
import cn.edu.scau.DataCollectionSystem.entity.Email;
import cn.edu.scau.DataCollectionSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/get")
    public ContactList getContactList() {
        List<Email> contacts = contactService.getAllContacts();
        return new ContactList().convertTo(contacts);
    }

    @RequestMapping(value = "/add")
    public BaseResponse addContact(@RequestBody Contact request) {
        Email newEmail = new Email()
                .setName(request.getName())
                .setAddress(request.getMail());

        return contactService.addContact(newEmail) ?
                new BaseResponse().setCode(10) :
                new BaseResponse().setCode(11);
    }

    @RequestMapping(value = "/delete")
    public BaseResponse deleteContact(@RequestBody DeleteInfo request) {
        contactService.deleteContact(request.getName());
        return new BaseResponse().setCode(10);
    }

}
