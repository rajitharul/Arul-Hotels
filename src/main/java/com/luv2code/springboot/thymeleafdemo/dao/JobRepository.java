package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Application;
import com.luv2code.springboot.thymeleafdemo.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {


    Set<Job> findByName(String name);
    Set<Job> findByCategory_Name(String categoryName);
}
