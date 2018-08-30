package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "follow_path")
@Getter
@Setter
@Accessors(chain = true)
public class FollowPath {

    @Field
    private String spider_name;

    @Field
    private String path_a;

    @Field
    private String path_all;

    @Field
    private String path_date;

    @Field
    private String path_tot;

    @Field
    private String url;

    public FollowPath(String spider_name, String url, String path_a, String path_all, String path_date, String path_tot) {
        this.spider_name = spider_name;
        this.url = url;
        this.path_a=path_a;
        this.path_all=path_all;
        this.path_date=path_date;
        this.path_tot=path_tot;
    }
}
