package cn.edu.scau.DataCollectionSystem.controller;

import cn.edu.scau.DataCollectionSystem.dto.request.DeleteInfo;
import cn.edu.scau.DataCollectionSystem.dto.request.NewSpider;
import cn.edu.scau.DataCollectionSystem.dto.request.PagingRequest;
import cn.edu.scau.DataCollectionSystem.dto.response.BaseResponse;
import cn.edu.scau.DataCollectionSystem.dto.response.SpiderList;
import cn.edu.scau.DataCollectionSystem.entity.Spider;
import cn.edu.scau.DataCollectionSystem.service.SpiderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/spider")
public class SpiderController {

    private final SpiderManagementService spiderService;

    @Autowired
    public SpiderController(SpiderManagementService spiderService) {
        this.spiderService = spiderService;
    }

    @RequestMapping(value = "/get")
    public SpiderList getSpider(@RequestBody PagingRequest request) {
        List<Spider> data =
                spiderService.getSpiderList(request.getPageNum() - 1, request.getPageSize());
        return new SpiderList().convertTo(data);
    }

    @RequestMapping(value = "/create")
    public BaseResponse createSpider(@RequestBody NewSpider request) {
        boolean r = spiderService.createSpider(
                request.getName(),
                request.getUrl(),
                request.getTitle1(),
                request.getUrl1(),
                request.getTitle2(),
                request.getUrl2());

        return r ? new BaseResponse().setCode(10) : new BaseResponse().setCode(11);
    }

    @RequestMapping(value = "/delete")
    public BaseResponse deleteSpider(@RequestBody DeleteInfo request) {
        return spiderService.removeSpider(request.getName()) ?
                new BaseResponse().setCode(10) :
                new BaseResponse().setCode(11);

    }
}
