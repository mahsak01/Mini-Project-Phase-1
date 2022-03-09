package com.example.uni.Services;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollegeService collegeService;

    /**
     * function for add new student
     * @param student input data
     * @return student
     */
    public Student addStudent(Student student,Long id)throws Exception{
        if (searchByNationalCode(student.getNationalCode())!=null ||searchByPersonnelNumber(student.getPersonnelNumber())!=null)
            throw new Exception("The student With This national code Exist");
        studentRepository.save(student);
        return collegeService.addStundet(student,id);
    }

    /**
     * function for update student
     * @param student input data
     * @param studentId      of student
     * @param collegeId      of college
     * @return student
     */
    public Student updateStudent(Student student , Long studentId , Long collegeId)throws Exception{
        student.setId(studentId);
        collegeService.deleteStundet(student,getStudent(studentId).getCollege().getId());
        studentRepository.save(student);
        return collegeService.addStundet(student,collegeId);
    }


    /**
     * function for delete student
     * @param id input data
     * @return student
     */
    public Student deleteStudent (Long id) throws Exception{

        Student student= getStudent(id);
        collegeService.deleteStundet(student,student.getCollege().getId());
        studentRepository.delete(student);
        return student;
    }

    /**
     * function for search student with national code
     * @param nationalCode input data
     * @return student
     */

    public Student searchByNationalCode(String nationalCode){
        return studentRepository.findByNationalCode(nationalCode);

    }

    /**
     * function for search student with personnel Number
     * @param personnelNumber input data
     * @return student
     */

    public Student searchByPersonnelNumber(String personnelNumber){
        return studentRepository.findByPersonnelNumber(personnelNumber);

    }

    /**
     * function for get all student
     * @return all student
     */
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    /**
     * function for get student
     * @param id input data
     * @return student
     */
    public Student getStudent (Long id )throws Exception{
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty())
            throw new Exception("The student With This id not Exist");
        return student.get();
    }


    /**
     * function for get all lesson of college
     * @param id
     * @return
     * @throws Exception
     */
    public Set<StudentLesson> getAllStudentLesson(Long id)throws Exception{

        return getStudent(id).getStudentLessons();
    }



}
