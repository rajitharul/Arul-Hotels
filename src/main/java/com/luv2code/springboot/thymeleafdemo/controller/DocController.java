package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.DocRepository;
import com.luv2code.springboot.thymeleafdemo.dao.FlightRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PilotRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Doc;
import com.luv2code.springboot.thymeleafdemo.service.DocStorageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;



import java.util.List;

@Controller
@RequestMapping("/docs")
public class DocController {

    @Autowired
    private DocStorageService docStorageService;

    @Autowired
    private DocRepository docRepository;




    @GetMapping("/get")
    public String get(Model model){

        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
            for (MultipartFile file:files){
                docStorageService.saveFile(file);
            }
            return "redirect:/";
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        Doc doc = docStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("docId") int theId){

        docRepository.deleteById(theId);

        return "redirect:/";

    }

}
