package cn.edu.scau.DataCollectionSystem.dto.response;

import cn.edu.scau.DataCollectionSystem.dto.Convertible;
import cn.edu.scau.DataCollectionSystem.entity.Spider;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class SpiderList implements Convertible<SpiderList, Spider> {

    List<SpiderInfo> result;

    @Override
    public SpiderList convertTo(List<Spider> spiders) {
        if (spiders.isEmpty())
            return this;

        List<SpiderInfo> results = new ArrayList<>();
        for (Spider spider : spiders)
            results.add(new SpiderInfo(spider));

        return setResult(results);
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    private class SpiderInfo {
        private String name;

        private String url;

        private String time;

        SpiderInfo(Spider spider) {
            this.name = spider.getSpiderName();
            this.url = spider.getUrl();
            this.time = spider.getCreateDate();
        }
    }

}

