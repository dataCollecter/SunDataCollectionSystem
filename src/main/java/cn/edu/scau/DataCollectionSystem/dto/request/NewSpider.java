package cn.edu.scau.DataCollectionSystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class NewSpider {

    private String name;

    private String url;

    private String title1;

    private String url1;

    private String title2;

    private String url2;
}
