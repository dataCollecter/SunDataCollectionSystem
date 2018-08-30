package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.FollowPath;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FollowPathDao extends MongoBase<FollowPath> {

    public void removeFollowPath(String spiderName) {
        Query query = new Query();
        query.addCriteria(new Criteria("spider_name").is(spiderName));
        this.remove(query, FollowPath.class);
    }
}
