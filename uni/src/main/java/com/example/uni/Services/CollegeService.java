package com.example.uni.Services;

import com.example.uni.Models.College;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
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
     *
     * @param college input data
     * @return college
     */
    public College addCollege(College college) throws Exception {
        if (searchByCollegeName(college.getCollegeName()) != null)
            throw new Exception("The college With This name Exist");
        return collegeRepository.save(college);
    }

    /**
     * function for update college
     *
     * @param college input data
     * @param id      of college
     * @return college
     */
    public College updateCollege(College college, Long id) throws Exception {
        college.setId(id);
        return addCollege(college);
    }


    /**
     * function for delete college
     *
     * @param id input data
     * @return college
     */
    public College deleteCollege(Long id) throws Exception {

        College college = getCollege(id);

        collegeRepository.delete(college);

        return college;
    }

    /**
     * function for search college with name
     *
     * @param collegeName input data
     * @return college
     */
    public College searchByCollegeName(String collegeName) {
        return collegeRepository.findByCollegeName(collegeName);
    }


    /**
     * function for get all college
     *
     * @return all college
     */
    public List<College> getAllCollege() {
        return collegeRepository.findAll();
    }

    /**
     * function for get college with id
     *
     * @param id input data
     * @return college
     */
    public College getCollege(Long id) throws Exception {
        Optional<College> college = collegeRepository.findById(id);
        if (college.isEmpty())
            throw new Exception("The college With This id not Exist");
        return college.get();
    }


    /**
     * function for get all lesson of college
     *
     * @param id of college
     * @return all lesson
     */
    public Set<Lesson> getAllLessonOfCollege(Long id) throws Exception {
        return getCollege(id).getLessons();
    }

    /**
     * function for get all Professor of college
     *
     * @param id of college
     * @return all Professor
     */
    public Set<Professor> getAllProfessorOfCollege(Long id) throws Exception {
        return getCollege(id).getProfessors();
    }

    /**
     * function for get all student of college
     *
     * @param id of college
     * @return all student
     */
    public Set<Student> getAllStudentOfCollege(Long id) throws Exception {
        return getCollege(id).getStudents();
    }

    /**
     * function for add student to college
     *
     * @param student input data
     * @param id      of college
     * @return student
     */
    public Student addStundet(Student student, Long id) throws Exception {
        College college = getCollege(id);
        college.getStudents().add(student);
        student.setCollege(college);
        collegeRepository.save(college);
        return student;
    }

    /**
     * function for delete student to college
     *
     * @param student input data
     * @param id      of college
     * @return student
     */
    public Student deleteStundet(Student student, Long id) throws Exception {
        College college = getCollege(id);
        college.getStudents().remove(student);
        student.setCollege(null);
        collegeRepository.save(college);
        return student;
    }
}
