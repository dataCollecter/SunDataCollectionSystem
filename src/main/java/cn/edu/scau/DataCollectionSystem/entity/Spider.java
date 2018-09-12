package cn.edu.scau.DataCollectionSystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Spider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String spiderName;

    private String url;

    private String createDate;

    private String title1;

    private String date1;

    private String title2;

    private String date2;

    public Spider() {
    }

    public Spider(String spiderName, String url, String createDate, String title1, String date1, String title2, String date2) {
        this.spiderName = spiderName;
        this.url = url;
        this.createDate = createDate;
        this.title1 = title1;
        this.date1 = date1;
        this.title2 = title2;
        this.date2 = date2;
    }
}
