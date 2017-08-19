package indi.yunhan.exam.mission.two.service;

import indi.yunhan.exam.mission.two.repository.StudentRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class CreateStudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String create(Map<String, String> jsonMap) {
        String stuId = jsonMap.get("stuId");
        String name = jsonMap.get("name");
        String gender = jsonMap.get("gender");
        String grade = jsonMap.get("grade");
        String college = jsonMap.get("college");
        String major = jsonMap.get("major");
        String classNum = jsonMap.get("classNum");

        return getStudentRepository().create(stuId, name, Integer.valueOf(gender), Integer.valueOf(grade), college, major, classNum);
    }
}
