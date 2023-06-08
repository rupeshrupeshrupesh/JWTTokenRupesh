package com.eazybyte.springsecuritybasic.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.eazybyte.springsecuritybasic.Model.Notice;
import com.eazybyte.springsecuritybasic.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public String getNotices() {
//        List<Notice> notices = noticeRepository.findAllActiveNotices();
//        if (notices != null ) {
//            return ResponseEntity.ok()
//                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
//                    .body(notices);
//        }else {
//            return null;
//        }

        return "hi i am in notices";
    }

}
