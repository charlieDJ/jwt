package com.example.boot.controller;

import com.example.boot.common.anno.ApiContainProperty;
import com.example.boot.common.anno.RateLimit;
import com.example.boot.common.enumeration.ImsiFlag;
import com.example.boot.model.Response;
import com.example.boot.model.request.OrderRequest;
import com.example.boot.model.response.TaskData;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URLEncoder;

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
     *
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
    public Response<Object> receiveEnum(@RequestBody TaskData data) {
        log.info(data.getImsiFlag().getDesc());
        return Response.success();
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws URISyntaxException, IOException {
        String filePath = "D:\\temp\\利息计算.xlsx";
        try (InputStream ins = new FileInputStream(new File(filePath));
             OutputStream out = response.getOutputStream()) {
            String outFileName = "利息计算.xlsx";
            String percentEncodedFileName = URLEncoder.encode(outFileName, "utf-8")
                    .replaceAll("\\+", "%20");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + percentEncodedFileName + ";filename*=utf-8''"
                    + percentEncodedFileName);
            IOUtils.copy(ins, out);
        }
    }
    @PostMapping("/query")
    @ApiOperation(value = "查询订单", notes = "查询订单")
    public Response<Object> query(@ApiContainProperty("id") @RequestBody OrderRequest request) {
        log.info("查询订单");
        return Response.success();
    }

}
