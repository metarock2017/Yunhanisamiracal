package indi.yunhan.exam.mission.two.service;

import indi.yunhan.exam.mission.two.repository.StudentRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class SearchService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public String search(Map<String, String> jsonMap) {
        int page = Integer.parseInt(jsonMap.get("page"));
        int per_page = Integer.parseInt(jsonMap.get("per_page"));
        String sort = jsonMap.get("sort");
        String sortby = jsonMap.get("sortby");
        String name = jsonMap.get("name");
        String stuId = jsonMap.get("stuId");

        return getStudentRepository().search(page,per_page,sort,sortby,name,stuId);
    }
}
