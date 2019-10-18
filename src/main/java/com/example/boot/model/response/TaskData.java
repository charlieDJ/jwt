package com.example.boot.model.response;

import com.example.boot.common.enumeration.ImsiFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dengjia
 * @date 2019/10/18 17:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaskData {
    private ImsiFlag imsiFlag;

    private Integer imsiNumber;
}
