package com.heewon.studentmanagement.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;
import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;

@Slf4j
@WebServlet(
        name = "frontServlet",
        urlPatterns = "*.do"
)
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        try{
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher servletDispatcher = req.getRequestDispatcher(servletPath);
            servletDispatcher.include(req, resp);

            String view = (String) req.getAttribute("view");
            String viewPath = view.substring(REDIRECT_PREFIX.length()+1);
            if(view.startsWith(REDIRECT_PREFIX)){
                log.error("redirect-url : {}", viewPath);
                resp.sendRedirect(viewPath);
            }else{
                RequestDispatcher viewDispatcher = req.getRequestDispatcher(viewPath);
                viewDispatcher.include(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getRequestURI());
            RequestDispatcher errorDispatcher = req.getRequestDispatcher("/error/error.jsp");
            errorDispatcher.forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath){
        int len = servletPath.length();
        return servletPath.substring(0, len - 3);
    }
}
