package cn.edu.scau.DataCollectionSystem.service;


import cn.edu.scau.DataCollectionSystem.dto.response.DataList;
import cn.edu.scau.DataCollectionSystem.entity.Data;

import java.util.List;

public interface DataService {

    List<Data> getData(int skip, int limit);

    DataList dataRequireHandler(int skip, int limit, String query);
}
