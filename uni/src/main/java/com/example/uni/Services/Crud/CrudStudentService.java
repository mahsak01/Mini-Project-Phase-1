package com.example.uni.Services.Crud;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.StudentRepository;
import com.example.uni.Services.Core.CoreStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CrudStudentService {

    @Autowired
    private CoreStudentService coreStudentService;


    /**
     * function for add new student
     *
     * @param studentDto input data
     * @return student
     */
    public Student addStudent(PersonDto studentDto) throws Exception {

        Student student = new Student(
                studentDto.getPersonnelNumber(),
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getNationalCode()
        );
        return coreStudentService.addStudent(student,studentDto.getCollegeId());
    }

    /**
     * function for update student
     *
     * @param studentDto input data
     * @param studentId  of student
     * @return student
     */
    public Student updateStudent(PersonDto studentDto, Long studentId) throws Exception {
        Student student = new Student(
                studentDto.getPersonnelNumber(),
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getNationalCode()
        );
        return coreStudentService.updateStudent(student,studentId,studentDto.getCollegeId());
    }


    /**
     * function for delete student
     *
     * @param id input data
     * @return student
     */
    public void deleteStudent(Long id) throws Exception {

        coreStudentService.deleteStudent(id);

    }

    /**
     * function for get all student
     *
     * @return all student
     */
    public List<Student> getAllStudent() {
        return coreStudentService.getAllStudent();
    }

    /**
     * function for get student
     *
     * @param id input data
     * @return student
     */
    public Student getStudent(Long id) throws Exception {
        return coreStudentService.getStudent(id);
    }


    /**
     * function for get all lesson of college
     *
     * @param id of student
     * @return all lesson
     */
    public Set<StudentLesson> getAllStudentLesson(Long id) throws Exception {

        return coreStudentService.getAllStudentLesson(id);
    }

    /**
     * function for get avg of student
     *
     * @param id of student
     * @return avg
     */
    public float getAvg(Long id) throws Exception {
        return coreStudentService.getAvg(id);
    }

}
