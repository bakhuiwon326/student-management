package com.heewon.studentmanagement.controller;

import com.heewon.studentmanagement.Command;
import com.heewon.studentmanagement.Gender;
import com.heewon.studentmanagement.bean.Student;
import com.heewon.studentmanagement.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getMethod().equals("GET")){
            return "/student/register.jsp";
        }
        else{
            StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String genderStr = request.getParameter("gender");
            String ageStr = (request.getParameter("age"));

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
            return "redirect:/student/view.do?id=" + id;
        }
    }
}