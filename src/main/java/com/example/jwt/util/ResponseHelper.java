package com.example.jwt.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseHelper {

    public static void setResponse(HttpServletResponse response, String json) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json);
    }
}
