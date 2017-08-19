package indi.yunhan.exam.mission.two.service;

import indi.yunhan.exam.mission.two.repository.StudentRepository;

/**
 * Created by asus on 2017/8/19.
 */
public class ShowStudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String show(){
        return getStudentRepository().show();
    }
}
