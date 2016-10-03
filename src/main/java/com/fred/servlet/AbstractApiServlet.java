package com.fred.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

abstract class AbstractApiServlet extends HttpServlet {

    /**
     * Baseado em: http://jaketrent.com/post/http-request-body-spring/
     *
     * @return String
     */
    protected String getBody(HttpServletRequest request) {
        String body = "";
        if (request.getMethod().equals("POST")) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader =  request.getReader();
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            } catch (IOException ex) {
                // swallow silently -- can't get body, won't
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        // swallow silently -- can't get body, won't
                    }
                }
            }
            body = sb.toString();
        }
        return body;
    }
}
