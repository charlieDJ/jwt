package com.example.boot.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author dj
 * @date 2021/3/15
 */
@ApiModel("订单请求")
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class OrderRequest {

    @ApiModelProperty(value = "订单ID")
    private String id;
    @ApiModelProperty(value = "订单支付状态")
    private String payStatus;
    @ApiModelProperty(value = "下单用户ID")
    private String memberId;
}
