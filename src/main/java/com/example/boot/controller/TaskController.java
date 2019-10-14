package com.example.boot.controller;

import com.example.boot.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Slf4j
@CrossOrigin
public class TaskController {

    @GetMapping
    public String listTasks() {
        return "任务列表";
    }

    @PostMapping
    public String newTasks() {
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public Response updateTasks(@PathVariable("taskId") Integer id) {
        log.info("测试一些内容");
        return Response.of("200", "更新了一下id为:" + id + "的任务");
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId") Integer id) {
        return "删除了id为:" + id + "的任务";
    }
}