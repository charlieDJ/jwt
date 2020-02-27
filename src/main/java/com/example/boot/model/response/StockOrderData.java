package com.example.boot.model.response;

import com.example.boot.common.enumeration.Level;
import lombok.Data;

@Data
public class StockOrderData {
    private Integer id;

    /**
     * 库存ID
     */
    private Integer sid;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 创建时间
     */
    private String createTime;

    private Level detailType;
}
