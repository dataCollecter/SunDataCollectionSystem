package cn.edu.scau.DataCollectionSystem.service.impl;

import cn.edu.scau.DataCollectionSystem.dao.SpiderDao;
import cn.edu.scau.DataCollectionSystem.entity.Spider;
import cn.edu.scau.DataCollectionSystem.service.SpiderManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SpiderManagementServiceImpl implements SpiderManagementService {

    private static Logger log = LoggerFactory.getLogger(SpiderManagementService.class);

    private final SpiderDao spiderDao;

    @Autowired
    public SpiderManagementServiceImpl(SpiderDao spiderDao) {
        this.spiderDao = spiderDao;
    }

    @Override
    public List<Spider> getSpiderList(int page, int pageSize) {
        Page<Spider> r = spiderDao.findAll(PageRequest.of(page, pageSize));
        return r.getContent();
    }

    public boolean removeSpider(String name) {
        Spider spider = spiderDao.findBySpiderName(name);
        if (spider == null)
            return false;

        spiderDao.delete(spider);

//        deleteSpider(name);

        return true;
    }

    public boolean createSpider(String name, String url, String title1, String date1, String title2, String date2) {
        if (spiderDao.findBySpiderName(name) != null)
            return false;

//        if (!createSpider(name))
//            return false;

        Date time = new Date();
        DateFormat d2 = DateFormat.getDateTimeInstance();
        String createDate = d2.format(time);
        Spider spider = new Spider(name, url, createDate, title1, date1, title2, date2);
        spiderDao.save(spider);
        return true;
    }

    private boolean createSpider(String spiderName) {
        String shpath = "/root/start_spider/" + spiderName + ".sh";
        String timing_path = "/var/spool/cron/crontabs/root";
        String body = "#!/bin/sh\ncd /root/dataCollecter\nscrapy crawl test -a spider_name=" + spiderName + "\n";
        try {
            Random random = new Random();
            File file = new File(shpath);
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(shpath);
            fileWriter.write(body);
            fileWriter.close();
            fileWriter = new FileWriter(timing_path, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(random.nextInt(61) + " 5 * * * " + shpath);//暂定
            printWriter.flush();
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
            Runtime.getRuntime().exec("chmod 777 " + shpath);
            Runtime.getRuntime().exec(shpath);
        } catch (Exception e) {
            log.error("Error At Create Spider : ", e);
            return false;
        }
        return true;
    }

    private void deleteSpider(String spiderName) {
        StringBuilder context = new StringBuilder();
        try {
            File file = new File("/root/start_spider/" + spiderName + ".sh");
            if (file.exists())
                file.delete();
            BufferedReader reader = new BufferedReader(new FileReader("/var/spool/cron/crontabs/root"));
            String line;
            Pattern pattern = Pattern.compile("/root/start_spider/" + spiderName + ".sh");
            Matcher matcher;
            while ((line = reader.readLine()) != null) {
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    continue;
                }
                context.append(line).append("\n");
            }
            reader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/var/spool/cron/crontabs/root"));
            bufferedWriter.write(context.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            log.error("Error At Delete Spider : ", e);
        }
    }

}
