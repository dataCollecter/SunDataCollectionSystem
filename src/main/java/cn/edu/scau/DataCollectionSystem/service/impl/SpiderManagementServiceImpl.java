package cn.edu.scau.DataCollectionSystem.service.impl;

import cn.edu.scau.DataCollectionSystem.dao.FollowPathDao;
import cn.edu.scau.DataCollectionSystem.dao.SpiderDao;
import cn.edu.scau.DataCollectionSystem.entity.Spider;
import cn.edu.scau.DataCollectionSystem.service.SpiderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SpiderDao spiderDao;

    private final FollowPathDao followPathDao;

    @Autowired
    public SpiderManagementServiceImpl(SpiderDao spiderDao, FollowPathDao followPathDao) {
        this.spiderDao = spiderDao;
        this.followPathDao = followPathDao;
    }

    @Override
    public List<Spider> getSpiderList(int skip, int limit) {
        return spiderDao.getSpider(skip, limit);
    }

    public boolean removeSpider(String name) {
        if (spiderDao.findSpider(name) == null)
            return false;

        spiderDao.removeSpider(name);
        followPathDao.removeFollowPath(name);
        StringBuilder context = new StringBuilder();
        try {
            File file = new File("/root/start_spider/" + name + ".sh");
            if (file.exists())
                file.delete();
            BufferedReader reader = new BufferedReader(new FileReader("/var/spool/cron/crontabs/root"));
            String line;
            Pattern pattern = Pattern.compile("/root/start_spider/" + name + ".sh");
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
            e.printStackTrace();
        }
        return true;
    }

    public boolean createSpider(String name, String url, String title1, String date1, String title2, String date2) {

        if (spiderDao.findSpider(name) != null)
            return false;

        String shpath = "/root/start_spider/" + name + ".sh";
        String timing_path = "/var/spool/cron/crontabs/root";
        String body = "#!/bin/sh\ncd /root/dataCollecter\nscrapy crawl test -a spider_name=" + name + "\n";
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
            e.printStackTrace();
            return false;
        }

        Date time = new Date();
        DateFormat d2 = DateFormat.getDateTimeInstance();
        String createDate = d2.format(time);
        Spider spider = new Spider(name, url, createDate, title1, date1, title2, date2);
        spiderDao.insert(spider);
        return true;
    }

}
