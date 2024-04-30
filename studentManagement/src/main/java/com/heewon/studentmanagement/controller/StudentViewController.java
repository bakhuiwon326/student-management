package com.heewon.studentmanagement.controller;

import com.heewon.studentmanagement.Command;
import com.heewon.studentmanagement.bean.Student;
import com.heewon.studentmanagement.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentViewController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        if(Objects.isNull(id)) throw new RuntimeException("id is null");
        Student selectedStudent = studentRepository.getStudentById(id);
        request.setAttribute("student", selectedStudent);
        return "/student/view.jsp";
    }
}