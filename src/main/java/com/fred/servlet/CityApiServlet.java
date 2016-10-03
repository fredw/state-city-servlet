package com.fred.servlet;

import com.fred.domain.CityDomain;
import com.fred.repository.CityRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/cities")
public class CityApiServlet extends AbstractApiServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            CityRepository cityRepository = new CityRepository();
            List<CityDomain> cities = cityRepository.findAll();
            response.getWriter().write(new Gson().toJson(cities));
        } catch (SQLException e) {
            response.getWriter().write(new Gson().toJson(e.toString()));
        }
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        HashMap<String,String> result = new HashMap<>();
        try {
            CityDomain city = new Gson().fromJson(this.getBody(request), CityDomain.class);
            CityRepository cityRepository = new CityRepository();
            cityRepository.save(city);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        response.getWriter().write(new Gson().toJson(result));
    }
}
