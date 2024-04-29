package com.heewon.studentmanagement.repository;

import com.heewon.studentmanagement.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentMap = new ConcurrentHashMap<>();
    @Override
    public void save(Student student) {
        if(Objects.isNull(student)) {throw new IllegalArgumentException("student is null");}
        studentMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        if(Objects.isNull(student)) {throw new IllegalArgumentException("student is null");}
        studentMap.put(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        if(Objects.isNull(id) || id.isEmpty()) {
            throw new IllegalArgumentException("student is null or empty");
        }
        if(!studentMap.containsKey(id)){
            throw new IllegalArgumentException("student not found");
        }
        studentMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        if(Objects.isNull(id) || id.isEmpty()) {
            throw new IllegalArgumentException("student is null or empty");
        }
        if(!studentMap.containsKey(id)){
            throw new IllegalArgumentException("student not found");
        }
        return new Student(studentMap.get(id));
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public boolean existById(String id) {
        if(Objects.isNull(id) || id.isEmpty()) {
            throw new IllegalArgumentException("student is null or empty");
        }
        return studentMap.containsKey(id);
    }

}
