package com.fred.servlet;

import com.fred.domain.StateDomain;
import com.fred.repository.StateRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/states/form")
public class StateFormServlet extends HttpServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        if (!request.getParameter("id").isEmpty()) {
            StateRepository stateRepository = new StateRepository();
            try {
                StateDomain state = stateRepository.get(Integer.valueOf(request.getParameter("id")));
                if (state != null) {
                    request.setAttribute("id", state.getId());
                    request.setAttribute("name", state.getName());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/state/form.jsp").forward(request, response);
    }
}
