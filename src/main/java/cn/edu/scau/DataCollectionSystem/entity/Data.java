package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Data")
@Getter
@Setter
@Accessors(chain = true)
public class Data {

    @Field
    private String title;

    @Field
    private String url;

    @Field
    private String spider;

    @Field
    private String date;

    public Data(String title, String url, String spider, String date) {
        this.title = title;
        this.url = url;
        this.spider = spider;
        this.date = date;
    }

}
