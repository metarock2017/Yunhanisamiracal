package indi.yunhan.exam.mission.two.service;

import indi.yunhan.exam.mission.two.repository.StudentRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class DeleteStudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String delete(Map<String,String> jsonMap){
        String stuId = jsonMap.get("stuId");

        return getStudentRepository().delete(stuId);
    }

}
