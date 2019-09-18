package com.example.boot.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ExcelUtils<T> {


    /**
     * 导出Excel
     *
     * @param list      集合
     * @param sheetName 表名
     * @param titles    标题
     * @param columns   字段名称
     * @param fileName  文件名
     * @param transType 翻译类型
     * @param <T>
     */
    public static <T> void export(List<T> list, String sheetName,
                                  String[] titles, String[] columns,
                                  String fileName, int transType) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        title(titles, wb, sheet);
        if (list.size() == 0) {
            log.error("未查询到数据");
            setHeaderWriteResponse(fileName, wb);
            return;
        }
        Class<?> clazz = list.get(0).getClass();
        for (int i = 0; i < list.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            for (int j = 0; j < columns.length; j++) {
                Cell cell = dataRow.createCell(j);
                Field field = null;
                Method method;
                Object value = null;
                try {
                    field = clazz.getDeclaredField(columns[j]);
                    method = clazz.getMethod(getGetter(columns[j]));
                    value = method.invoke(list.get(i));
                } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    try {
                        field = clazz.getSuperclass().getDeclaredField(columns[j]);
                        method = clazz.getSuperclass().getMethod(getGetter(columns[j]));
                        value = method.invoke(list.get(i));
                    } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
                        log.error(e1.getMessage(), e1);
                    }
                }
                if (Objects.isNull(field)) {
                    log.error(String.format("未查到字段属性：%s", columns[j]));
                    continue;
                }
                trans(field, value, cell, transType);
            }
        }
        setHeaderWriteResponse(fileName, wb);
    }

    private static void trans(Field field, Object value, Cell cell, int transType) {
        cell.setCellValue(value == null ? "" : value.toString());
    }



    public static String translateTimeStamp(Object value) {
        if (Objects.isNull(value)) {
            return "";
        }
        long time = (long) value;
        if (time == 0L) {
            return "";
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return df.format(dateTime);
    }

    public static String changeF2Y(Object value) {
        Long amount;
        if (value instanceof Integer) {
            amount = ((Integer) value).longValue();
        } else {
            amount = (Long) value;
        }
        return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).toString();
    }


    private static String getGetter(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static void setHeaderWriteResponse(String fileName, Workbook workbook) {
        try {
            HttpServletRequest request = ServletUtils.servletRequest();
            HttpServletResponse response = ServletUtils.servletResponse();
            String userAgent = request.getHeader("User-Agent");
            // 解决中文乱码问题
            String fileName1 = "Excel-" + fileName + ".xlsx";
            String newFilename = URLEncoder.encode(fileName1, "UTF-8");
            // 如果没有userAgent，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
            String rtn = "filename=\"" + newFilename + "\"";
            if (userAgent != null) {
                userAgent = userAgent.toUpperCase();
                // IE浏览器，只能采用URLEncoder编码
                if (userAgent.contains("IE")) {
                    rtn = "filename=\"" + newFilename + "\"";
                }
                // Opera浏览器只能采用filename*
                else if (userAgent.contains("OPERA")) {
                    rtn = "filename*=UTF-8''" + newFilename;
                }
                // Safari浏览器，只能采用ISO编码的中文输出
                else if (userAgent.contains("SAFARI")) {
                    rtn = "filename=\"" + new String(fileName1.getBytes(StandardCharsets.UTF_8), "ISO8859-1")
                            + "\"";
                }
                // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                else if (userAgent.contains("FIREFOX")) {
                    rtn = "filename*=UTF-8''" + newFilename;
                }
            }

            String headStr = "attachment;  " + rtn;
            response.setContentType("APPLICATION/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", headStr);
            // 响应到客户端
            OutputStream os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置标题
     *
     * @param titles 标题
     * @param wb     工作簿
     * @param sheet  表名
     */
    private static void title(String[] titles, XSSFWorkbook wb, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < titles.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
    }
}
