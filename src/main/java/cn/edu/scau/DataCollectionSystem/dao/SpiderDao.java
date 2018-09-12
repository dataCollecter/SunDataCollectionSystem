package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.Spider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpiderDao extends JpaRepository<Spider, Integer> {

    Page<Spider> findAll(Pageable pageable);

    Spider findBySpiderName(String spiderName);

}
