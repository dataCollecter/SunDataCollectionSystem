package cn.edu.scau.DataCollectionSystem.dto.response;

import cn.edu.scau.DataCollectionSystem.dto.Convertible;
import cn.edu.scau.DataCollectionSystem.entity.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class DataList implements Convertible<DataList, Data> {

    private int draw;

    private String recordsTotal;

    private String recordsFiltered;

    private List<DataInfo> data;

    @Override
    public DataList convertTo(List<Data> datas) {
        if (datas.isEmpty())
            return this;

        List<DataInfo> results = new ArrayList<>();
        for (Data data : datas)
            results.add(new DataInfo(data));

        return setData(results);
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    private class DataInfo {
        private String source;

        private String time;

        private String title;

        private String url;

        DataInfo(Data data) {
            this.source = data.getSpider();
            this.time = data.getDate();
            this.title = data.getTitle();
            this.url = data.getUrl();
        }
    }
}
