package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.Email;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailDao extends MongoBase<Email> {

    public List<Email> getContactList() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "_id"));
        return this.find(query, Email.class);
    }

    public List<Email> getContactList(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "_id"));
        query.skip(skip).limit(limit);
        return this.find(query, Email.class);
    }

    public Email findContact(String name) {
        Query query = new Query();
        query.addCriteria(new Criteria("name").is(name));
        return this.findOne(query, Email.class);
    }

    public boolean deleteContact(String name) {
        Query query = new Query();
        query.addCriteria(new Criteria("name").is(name));
        return this.remove(query, Email.class);
    }

}
