package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.ApplicationRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Application;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CsvExportService {


    private  ApplicationRepository applicationRepository;

    public CsvExportService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void writeEmployeesToCsv(Writer writer) {

        List<Application> applications = applicationRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            csvPrinter.printRecord("id" , "firstName" , "lastName"  ,"status" , "job" , "category" , "mobile" , "email");

            for (Application application : applications) {
                csvPrinter.printRecord(application.getId(), application.getFirstName(), application.getLastName(),application.getStatus().getName()
                ,application.getJobs().iterator().next().getName() ,application.getJobs().iterator().next().getCategory().getName() , application.getMobile() , application.getEmail());
            }


        } catch (IOException e) {

        }
    }
}
