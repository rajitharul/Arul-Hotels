package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.DocRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Application;
import com.luv2code.springboot.thymeleafdemo.entity.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DocStorageService {

    @Autowired
    private DocRepository docRepository;

    public Doc saveFile(MultipartFile file){
        String docname = file.getOriginalFilename();

        try {
            Doc doc = new Doc(docname, file.getContentType(), file.getBytes());
            return docRepository.save(doc);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public Doc saveFile(MultipartFile file , Application application){
        String docname = file.getOriginalFilename();

        try {
            Doc doc = new Doc(docname, file.getContentType(), file.getBytes() , application);
            return docRepository.save(doc);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Optional<Doc> getFile(Integer fileId){
        return docRepository.findById(fileId);
    }

    public List<Doc> getFiles(){
        return docRepository.findAll();

    }

}
