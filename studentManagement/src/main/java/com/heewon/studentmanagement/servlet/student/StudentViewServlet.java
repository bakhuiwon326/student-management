package com.heewon.studentmanagement.servlet.student;

import com.heewon.studentmanagement.bean.Student;
import com.heewon.studentmanagement.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "studentViewServlet",
        urlPatterns = "/student/view"
)
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init() throws ServletException {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(Objects.isNull(id)) throw new RuntimeException("id is null");
        Student selectedStudent = studentRepository.getStudentById(id);
        req.setAttribute("student", selectedStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/student/view.jsp");
        dispatcher.forward(req, resp);
    }
}
