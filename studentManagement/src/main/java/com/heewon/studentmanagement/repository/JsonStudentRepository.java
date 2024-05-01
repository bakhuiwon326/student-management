package com.heewon.studentmanagement.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.heewon.studentmanagement.bean.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonStudentRepository implements StudentRepository{
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "student.json";
    public JsonStudentRepository(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File(JSON_FILE_PATH);
        if(file.exists()){
            file.delete();
        }
    }

    private synchronized List<Student> readJsonFile(){
        File file = new File(JSON_FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        // 역직렬화 (json string -> object)
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
            ArrayList<Student >students = objectMapper.readValue(bufferedReader, new TypeReference<ArrayList<Student>>() {});
            return students;
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList){
        File file = new File(JSON_FILE_PATH);
        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);){
            objectMapper.writeValue(bufferedWriter, studentList);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile();
        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(student.getId())){
                students.set(i, student);
                break;
            }
        }
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.remove(i);
                break;
            }
        }
        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        Student res = null;
        List<Student> students = readJsonFile();
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                res = new Student(students.get(i));
                break;
            }
        }
        return res;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        boolean res = false;
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                res = true;
                break;
            }
        }
        return res;
    }
}
