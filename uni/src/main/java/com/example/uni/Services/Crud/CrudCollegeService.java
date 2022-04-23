package com.example.uni.Services.Crud;

import com.example.uni.Models.College;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Repositories.CollegeRepository;
import com.example.uni.Services.Core.CoreCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CrudCollegeService {

    @Autowired
    private CoreCollegeService coreCollegeService;


    /**
     * function for add new college
     * @param college input data
     */
    public void addCollege(College college) throws Exception {
        coreCollegeService.addCollege(college);
    }

    /**
     * function for update college
     * @param college input data
     * @param id      of college
     */
    public void updateCollege(College college, Long id) throws Exception {
       coreCollegeService.updateCollege(college,id);
    }


    /**
     * function for delete college
     * @param id input data
     */
    public void deleteCollege(Long id) throws Exception {
        coreCollegeService.deleteCollege(id);
    }

    /**
     * function for get all college
     * @return all college
     */
    public List<College> getAllCollege() {
        return coreCollegeService.getAllCollege();
    }

    /**
     * function for get college with id
     * @param id input data
     * @return college
     */
    public College  getCollege(Long id) throws Exception {
        return coreCollegeService.getCollege(id);
    }


    /**
     * function for get all lesson of college
     * @param id of college
     * @return all lesson
     */
    public Set<Lesson> getAllLessonOfCollege(Long id) throws Exception {
        return coreCollegeService.getAllLessonOfCollege(id);
    }

    /**
     * function for get all Professor of college
     * @param id of college
     * @return all Professor
     */
    public Set<Professor> getAllProfessorOfCollege(Long id) throws Exception {
        return coreCollegeService.getAllProfessorOfCollege(id);

    }

    /**
     * function for get all student of college
     * @param id of college
     * @return all student
     */
    public Set<Student> getAllStudentOfCollege(Long id) throws Exception {
        return coreCollegeService.getAllStudentOfCollege(id);

    }
}
