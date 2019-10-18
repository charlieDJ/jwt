package com.example.boot.controller;

import com.example.boot.common.anno.RateLimit;
import com.example.boot.common.enumeration.ImsiFlag;
import com.example.boot.model.Response;
import com.example.boot.model.response.TaskData;
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

    /**
     * 10 秒中，可以访问10次
     * @param id
     * @return
     */
    @RateLimit(key = "test", time = 10, count = 10)
    @PutMapping("/{taskId}")
    public Response<TaskData> updateTasks(@PathVariable("taskId") Integer id) {
        log.info("测试一些内容");
        TaskData data = new TaskData();
        data.setImsiFlag(ImsiFlag.OCCUPY);
        return Response.success(data);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId") Integer id) {
        return "删除了id为:" + id + "的任务";
    }

    @PostMapping("/enum")
    public Response receiveEnum(@RequestBody TaskData data) {
        log.info(data.getImsiFlag().getDesc());
        return Response.success();
    }
}
