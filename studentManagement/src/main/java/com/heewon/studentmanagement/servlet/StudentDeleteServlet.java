package com.heewon.studentmanagement.servlet;

import com.heewon.studentmanagement.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(
        name = "studentDeleteServlet",
        urlPatterns = "/student/delete"
)
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;
    @Override
    public void init() throws ServletException {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            throw new RuntimeException("id is null");
        }
        studentRepository.deleteById(id);
        resp.sendRedirect("/student/list");
    }
}
