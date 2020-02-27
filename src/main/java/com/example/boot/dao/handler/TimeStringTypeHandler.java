package com.example.boot.dao.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(String.class)
@Slf4j
public class TimeStringTypeHandler implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String value = (String) parameter;
        ps.setString(i, value);
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return this.formatTime(rs.getString(columnName));
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.formatTime(rs.getString(columnIndex));
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.formatTime(cs.getString(columnIndex));
    }

    private String formatTime(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return timeStr;
        }

        // 2019-02-20 10:00:00
        try {
            return timeStr.substring(0, 19);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeStr;
    }

}
