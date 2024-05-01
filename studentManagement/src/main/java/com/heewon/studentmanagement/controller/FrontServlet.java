package com.heewon.studentmanagement.controller;

import com.heewon.studentmanagement.Command;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.exception.util.ExceptionContextProvider;

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
    private static final String REDIRECT_PREFIX = "redirect";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        Command command = getCommand(req);
        String view = command.execute(req, resp);

        try{
            if(view.startsWith(REDIRECT_PREFIX)){
                String viewPath = view.substring(REDIRECT_PREFIX.length() + 1);
                resp.sendRedirect(viewPath);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher(view);
                dispatcher.include(req, resp);
            }
        } catch(Exception e){
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }
    }

    private Command getCommand(HttpServletRequest req){
        Command command = null;
        String servlePath = req.getServletPath();
        int n = servlePath.length();
        String path = servlePath.substring(0, n-3);
        String method = req.getMethod();
        if(path.equals("/student/list") && method.equals("GET")){
            command = new StudentListController();
        }
        else if(path.equals("/student/delete") && method.equals("POST")){
            command = new StudentDeleteController();
        }
        else if(path.equals("/student/register") && method.equals("POST")){
            command = new StudentRegisterController();
        }
        else if(path.equals("/student/register") && method.equals("GET")){
            command = new StudentRegisterController();
        }
        else if(path.equals("/student/update") && method.equals("POST")){
            command = new StudentUpdateController();
        }
        else if(path.equals("/student/update") && method.equals("GET")){
            command = new StudentUpdateController();
        }
        else if(path.equals("/student/view") && method.equals("GET")){
            command = new StudentViewController();
        }
        else if(path.equals("/error")){
            command = new ErrorController();
        }
        return command;
    }
}
