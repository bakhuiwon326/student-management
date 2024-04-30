package com.heewon.studentmanagement.servlet;

import com.heewon.studentmanagement.Gender;
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
        name = "studentRegister",
        urlPatterns = "/student/register"
)
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init() throws ServletException {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "/student/register");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/student/register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderStr = req.getParameter("gender");
        String ageStr = (req.getParameter("age"));

        if(
                Objects.isNull(id) || Objects.isNull(name) ||Objects.isNull(genderStr) ||Objects.isNull(ageStr)
                || id.isEmpty() || name.isEmpty() || genderStr.isEmpty() || ageStr.isEmpty()
                || Integer.parseInt(ageStr) <= 0
        ){
            throw new RuntimeException();
        }

        Gender gender = Gender.valueOf(genderStr);
        int age = Integer.parseInt(ageStr);
        studentRepository.save(new Student(id, name, gender, age));

        String redirect = "/student/view?id=" + id;
        resp.setCharacterEncoding("utf-8");
        resp.sendRedirect(redirect);
    }
}
