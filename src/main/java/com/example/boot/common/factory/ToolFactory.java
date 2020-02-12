package com.example.boot.common.factory;

import com.example.boot.dao.model.Tool;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

@Data
public class ToolFactory implements FactoryBean<Tool> {

    private int factoryId;
    private int toolId;

    @Override
    public Tool getObject() throws Exception {
        return new Tool(toolId);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }
}
