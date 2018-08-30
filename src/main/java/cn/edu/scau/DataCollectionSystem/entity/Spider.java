package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Spider")
@Getter
@Setter
@Accessors(chain = true)
public class Spider {

    @Field
    private String spider_name;

    @Field
    private String url;

    @Field
    private String createDate;

    @Field
    private String title1;

    @Field
    private String date1;

    @Field
    private String title2;

    @Field
    private String date2;

    public Spider(String spider_name, String url, String createDate, String title1, String date1, String title2, String date2) {
        this.spider_name = spider_name;
        this.url = url;
        this.createDate = createDate;
        this.title1 = title1;
        this.date1 = date1;
        this.title2 = title2;
        this.date2 = date2;
    }
}
