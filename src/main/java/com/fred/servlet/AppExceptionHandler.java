package com.fred.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

@WebServlet("/error")
public class AppExceptionHandler extends HttpServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, ServletException {
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (request.getContentType().contains("application/json")) {

            response.setContentType("application/json");

            HashMap<String,String> result = new HashMap<>();
            result.put("code", code.toString());
            result.put("message", message);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);

            result.put("stackTrace", sw.toString());
            response.getWriter().write(new Gson().toJson(result));
        } else {
            response.setContentType("text/html");

            request.setAttribute("code", code);
            request.setAttribute("message", message);
            request.setAttribute("uri", requestUri);
            request.setAttribute("servletName", servletName);
            request.setAttribute("exceptionName", (throwable != null ? throwable.getClass().getName() : ""));
            request.setAttribute("exceptionMessage", (throwable != null ? throwable.getMessage() : ""));

            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
