package cn.edu.scau.DataCollectionSystem.service.impl;

import cn.edu.scau.DataCollectionSystem.dao.EmailDao;
import cn.edu.scau.DataCollectionSystem.entity.Email;
import cn.edu.scau.DataCollectionSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final EmailDao emailDao;

    @Autowired
    public ContactServiceImpl(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Override
    public List<Email> getAllContacts() {
        return emailDao.findAll();
    }

    @Override
    public List<Email> getContacts(int page, int pageSize) {
        Page<Email> r = emailDao.findAll(PageRequest.of(page, pageSize));
        return r.getContent();
    }

    @Override
    public boolean addContact(Email newEmail) {
        //查重
        if(emailDao.findByName(newEmail.getName()) != null)
            return false;

        newEmail.setEnable(true);
        emailDao.save(newEmail);

        return true;
    }

    @Override
    public void deleteContact(String name) {
        //判断联系人是否存在
        Email email = emailDao.findByName(name);
        if(email == null)
            return;
        emailDao.delete(email);
    }
}
