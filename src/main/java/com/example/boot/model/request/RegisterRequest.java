package com.example.boot.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author dengjia
 * @date 2019/8/15 16:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("注册请求实体")
public class RegisterRequest {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(example = "张三", value = "姓名" , required = true)
    private String username;
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(example = "123456", value = "密码" , required = true)
    private String password;
}
