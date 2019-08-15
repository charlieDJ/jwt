package com.example.boot.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author dengjia
 * @date 2019/8/15 18:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("登录请求")
public class UmsAdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true, example = "张三")
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
