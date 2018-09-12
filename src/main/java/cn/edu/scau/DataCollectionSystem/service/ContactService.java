package cn.edu.scau.DataCollectionSystem.service;

import cn.edu.scau.DataCollectionSystem.entity.Email;

import java.util.List;

public interface ContactService {

    /**
     * 获取联系人列表
     * @return
     */
    List<Email> getAllContacts();

    /**
     * 获取联系人列表
     * @return
     */
    List<Email> getContacts(int page, int pageSize);

    /**
     * 添加联系人
     * @param newEmail
     * @return  执行结果
     */
    boolean addContact(Email newEmail);

    /**
     * 删除联系人
     * @param name  要删除的联系人
     * @return  删除结果
     */
    void deleteContact(String name);
}
