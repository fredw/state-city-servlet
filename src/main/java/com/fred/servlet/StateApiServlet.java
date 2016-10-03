package com.fred.servlet;

import com.fred.domain.StateDomain;
import com.fred.repository.StateRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/states")
public class StateApiServlet extends AbstractApiServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            StateRepository stateRepository = new StateRepository();
            List<StateDomain> states = stateRepository.findAll();
            response.getWriter().write(new Gson().toJson(states));
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
            StateDomain state = new Gson().fromJson(this.getBody(request), StateDomain.class);
            StateRepository stateRepository = new StateRepository();
            stateRepository.save(state);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        response.getWriter().write(new Gson().toJson(result));
    }

    protected void doDelete(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        HashMap<String,String> result = new HashMap<>();
        try {
            StateRepository stateRepository = new StateRepository();
            StateDomain state = new Gson().fromJson(request.getReader(), StateDomain.class);
            stateRepository.delete(state);
            result.put("status", "success");
        } catch (SQLException e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        response.getWriter().write(new Gson().toJson(result));
    }
}
