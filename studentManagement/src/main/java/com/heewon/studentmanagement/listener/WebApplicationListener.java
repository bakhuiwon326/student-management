package com.heewon.studentmanagement.listener;

import com.heewon.studentmanagement.Gender;
import com.heewon.studentmanagement.bean.Student;
import com.heewon.studentmanagement.repository.MapStudentRepository;
import com.heewon.studentmanagement.repository.StudentRepository;
import org.apache.commons.math3.random.RandomDataGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// 웹 애플리케이션 시작 및 종료 이벤트를 listen
@WebListener
public class WebApplicationListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("웹 구동.");
        ServletContext servletContext = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Gender[] genders = {Gender.M, Gender.F};
        for(int i = 1; i <= 10; i++){
            int randomGenderIdx = new RandomDataGenerator().nextInt(0, 1);
            Gender randomGender = genders[randomGenderIdx];
            int randomAge = new RandomDataGenerator().nextInt(20, 31);
            studentRepository.save(new Student(String.format("stu%d", i), "학생" + i, randomGender, randomAge));
        }
        servletContext.setAttribute("studentRepository", studentRepository);
    }
}
