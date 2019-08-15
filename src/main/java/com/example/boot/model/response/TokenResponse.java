package com.example.boot.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dengjia
 * @date 2019/8/15 18:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("token响应")
public class TokenResponse {
    @ApiModelProperty(example = "13131", value = "token")
    private String token;
    @ApiModelProperty(example = "4561651", value = "token头")
    private String tokenHead;
}
