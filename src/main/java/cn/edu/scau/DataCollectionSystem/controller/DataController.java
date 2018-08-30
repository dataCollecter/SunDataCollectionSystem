package cn.edu.scau.DataCollectionSystem.controller;

import cn.edu.scau.DataCollectionSystem.dto.response.DataList;
import cn.edu.scau.DataCollectionSystem.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/data")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(value = "/get")
    public DataList getData(HttpServletRequest request) {
        int draw = Integer.parseInt(request.getParameter("draw"));
        int limit = Integer.parseInt(request.getParameter("length"));
        int skip = Integer.parseInt(request.getParameter("start"));
        String query = request.getParameter("search[value]");

        return dataService.dataRequireHandler(skip, limit, query)
                .setDraw(draw);
    }
}