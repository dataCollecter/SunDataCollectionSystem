package cn.edu.scau.DataCollectionSystem.dao;

import cn.edu.scau.DataCollectionSystem.entity.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmailDao extends JpaRepository<Email, Integer> {

    Page<Email> findAll(Pageable pageable);

    Email findByName(String name);
}
