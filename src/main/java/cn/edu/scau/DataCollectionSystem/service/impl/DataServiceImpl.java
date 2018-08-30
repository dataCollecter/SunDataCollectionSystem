package cn.edu.scau.DataCollectionSystem.service.impl;

import cn.edu.scau.DataCollectionSystem.dao.DataDao;
import cn.edu.scau.DataCollectionSystem.dto.response.DataList;
import cn.edu.scau.DataCollectionSystem.entity.Data;
import cn.edu.scau.DataCollectionSystem.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private final DataDao dataDao;

    @Autowired
    public DataServiceImpl(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @Override
    public List<Data> getData(int skip, int limit) {
        return dataDao.getData(skip, limit);
    }

    @Override
    public DataList dataRequireHandler(int skip, int limit, String query) {
        List<Data> datas;
        long count = dataDao.getCount();
        long filter;

        if(query.isEmpty()) {
            datas =  titleFilter(getData(skip, limit));
            filter = count;
        }
        else {
            datas = titleFilter(queryData(skip, limit, query));
            filter = dataDao.getCount(query);
        }

        return new DataList()
                .setRecordsTotal(String.valueOf(count))
                .setRecordsFiltered(String.valueOf(filter))
                .convertTo(datas);
    }

    private List<Data> queryData(int skip, int limit, String query) {
        return dataDao.queryData(query, skip, limit);
    }

    private List<Data> titleFilter(List<Data> dataList) {
        for (Data d : dataList) {
            if (d.getTitle().length() > 40)
                d.setTitle(
                        d.getTitle().substring(0, 39)
                                + "...");
        }
        return dataList;
    }

}
