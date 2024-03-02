package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojo.Attendence;
import java.util.List;
import java.util.Date;
import com.app.pojo.User;



public interface AttendenceDao extends JpaRepository<Attendence, Long>{
           public List<Attendence> findByUserAndDate(User user, Date date);
           public List<Attendence> findByUser(User user);
          
           @Query("SELECT a FROM Attendence a WHERE a.user.id = :userId ORDER BY a.id DESC limit 1")
           Attendence findTopByUserIdOrderByidDesc(@Param("userId") int id);

}

