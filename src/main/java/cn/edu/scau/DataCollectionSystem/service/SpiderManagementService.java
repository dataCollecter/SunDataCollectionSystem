package cn.edu.scau.DataCollectionSystem.service;

import cn.edu.scau.DataCollectionSystem.entity.Spider;

import java.util.List;

public interface SpiderManagementService {

    List<Spider> getSpiderList(int skip, int limit);

    boolean removeSpider(String name);

    boolean createSpider(String name, String url, String title1, String date1, String title2, String date2);

}
