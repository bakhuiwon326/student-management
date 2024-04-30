package com.heewon.studentmanagement.servlet;


import com.heewon.studentmanagement.bean.Student;
import com.heewon.studentmanagement.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "studentListServlet",
        urlPatterns = "/student/list"
)
public class StudentListServlet extends HttpServlet {
    StudentRepository studentRepository;

    @Override
    public void init() throws ServletException {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);
        //req.getRequestDispatcher("/student/list.jsp").forward(req, resp);

        req.setAttribute("view", "/student/list.jsp");

    }
}