package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.Spider;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpiderDao extends MongoBase<Spider> {

    public List<Spider> getSpiderList() {
        return this.findAll(Spider.class);
    }

    public List<Spider> getSpider(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "createDate"));
        query.skip(skip).limit(limit);

        return this.find(query, Spider.class);
    }

    public Spider findSpider(String name) {
        Query query = new Query();
        query.addCriteria(new Criteria("spider_name").is(name));
        return this.findOne(query, Spider.class);
    }

    public void removeSpider(String name) {
        Query query = new Query();
        query.addCriteria(new Criteria("spider_name").is(name));
        this.remove(query, Spider.class);
    }

}
