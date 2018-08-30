package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDao extends MongoBase<Data> {

    public List<Data> getData() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));

        return this.find(query, Data.class);
    }

    public List<Data> getData(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        query.skip(skip).limit(limit);

        return this.find(query, Data.class);
    }
    public List<Data> queryData(String key, int skip, int limit) {
        Query query = buildQuery(key);
        query.skip(skip).limit(limit);
        return this.find(query, Data.class);
    }

    public long getCount() {
        return this.count(new Query(), Data.class);
    }

    public long getCount(String query) {
        return this.count(buildQuery(query), Data.class);
    }

    private Query buildQuery(String key) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("spider").regex(".*" + key + ".*"),
                Criteria.where("title").regex(".*" + key + ".*"),
                Criteria.where("date").regex(".*" + key + ".*")
        ));
        return query;
    }

}
