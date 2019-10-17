package com.example.boot.controller;

import com.example.boot.model.Response;
import com.example.boot.service.OrderService;
import com.example.boot.service.StockService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengjia
 * @date 2019/10/17 14:25
 */
@Api(tags = {"订单接口"})
@RestController
@RequestMapping("/order")
@Validated
@Slf4j
public class OrderController {


    @Autowired
    private StockService stockService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public Response createWrongOrder(@PathVariable int sid) {
        log.info("sid=[{}]", sid);
        int id = orderService.createWrongOrder(sid);
        return Response.success(id);
    }

}
