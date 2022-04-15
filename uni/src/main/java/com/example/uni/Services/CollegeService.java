package com.example.uni.Services;

import com.example.uni.Models.*;
import com.example.uni.Repositories.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;


    /**
     * function for add new college
     * @param college input data
     */
    public void addCollege(College college) throws Exception {
        if (searchByCollegeName((String) Models.getField(college,"collegeName")) != null)
            throw new Exception("The college With This name Exist");
        collegeRepository.save(college);
    }

    /**
     * function for update college
     * @param college input data
     * @param id      of college
     */
    public void updateCollege(College college, Long id) throws Exception {
        if (getCollege(id)==null)
            throw new Exception("The college With This id not Exist");
        Models.setField(college,"id",id);
        addCollege(college);
    }


    /**
     * function for delete college
     * @param id input data
     */
    public void deleteCollege(Long id) throws Exception {

        College college = getCollege(id);

        collegeRepository.delete(college);

    }

    /**
     * function for search college with name
     * @param collegeName input data
     * @return college
     */
    public College searchByCollegeName(String collegeName) {
        return collegeRepository.findByCollegeName(collegeName);
    }


    /**
     * function for get all college
     * @return all college
     */
    public List<College> getAllCollege() {
        return collegeRepository.findAll();
    }

    /**
     * function for get college with id
     * @param id input data
     * @return college
     */
    public College  getCollege(Long id) throws Exception {
        Optional<College> college = collegeRepository.findById(id);
        if (college.isEmpty())
            throw new Exception("The college With This id not Exist");
        return college.get();
    }


    /**
     * function for get all lesson of college
     * @param id of college
     * @return all lesson
     */
    public Set<Lesson> getAllLessonOfCollege(Long id) throws Exception {
        return (Set<Lesson>) Models.getField(getCollege(id),"lessons");
    }

    /**
     * function for get all Professor of college
     * @param id of college
     * @return all Professor
     */
    public Set<Professor> getAllProfessorOfCollege(Long id) throws Exception {
        return (Set<Professor>) Models.getField(getCollege(id),"professors");

    }

    /**
     * function for get all student of college
     * @param id of college
     * @return all student
     */
    public Set<Student> getAllStudentOfCollege(Long id) throws Exception {
        return (Set<Student>) Models.getField(getCollege(id),"students");

    }
}
