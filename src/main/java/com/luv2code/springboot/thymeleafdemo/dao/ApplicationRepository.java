package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Application;
import com.luv2code.springboot.thymeleafdemo.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {



List<Application> findByMobile(int mobile);

List<Application> findByJobsIn(Set<Job> jobs);

List<Application> findByStatusId(int Id);



}
