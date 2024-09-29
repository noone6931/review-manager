package com.chengming.reviewmanager.interfaces.controller;

import com.chengming.reviewmanager.application.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ProjectService projectService;

    @GetMapping("/testInsertProject")
    public void testInsertProject(){
        projectService.batchInsert();
    }

}
