package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends MongoBase<User> {

    @Deprecated
    public User verifyLogin(String password) {
        Query query = new Query();
        query.addCriteria(new Criteria("password").is(password));
        return this.findOne(query, User.class);
    }

}
