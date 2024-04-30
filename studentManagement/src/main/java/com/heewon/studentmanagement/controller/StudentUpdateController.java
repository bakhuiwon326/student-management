package com.heewon.studentmanagement.controller;

import com.heewon.studentmanagement.Command;
import com.heewon.studentmanagement.Gender;
import com.heewon.studentmanagement.bean.Student;
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
public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        if(request.getMethod().equals("GET")){
            String id = request.getParameter("id");
            if(Objects.isNull(id)) throw new RuntimeException("id is null");
            Student selectedStudent = studentRepository.getStudentById(id);
            request.setAttribute("student", selectedStudent);
            return "/student/register.jsp";
        }
        else{
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String genderStr = request.getParameter("gender");
            String ageStr = (request.getParameter("age"));

            if(
                    Objects.isNull(id) || Objects.isNull(name) ||Objects.isNull(genderStr) ||Objects.isNull(ageStr)
                            || id.isEmpty() || name.isEmpty() || genderStr.isEmpty() || ageStr.isEmpty()
                            || Integer.parseInt(ageStr) <= 0
            ){
                throw new RuntimeException("null or invalid value");
            }
            Gender gender = Gender.valueOf(genderStr);
            int age = Integer.parseInt(ageStr);
            Student updatedStudent = new Student(id, name, gender, age);
            studentRepository.update(updatedStudent);

            return "redirect:/student/view.do?id=" + id;
        }
    }
}