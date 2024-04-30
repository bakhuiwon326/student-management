package com.heewon.studentmanagement.servlet.student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

@WebServlet(
        name = "errorServlet",
        urlPatterns = "/error"
)
public class ErrorServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Error Servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Error Servlet - get method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Error Servlet - post method");
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getRequestURI());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error/error.jsp");
        dispatcher.forward(req, resp);
    }

}
