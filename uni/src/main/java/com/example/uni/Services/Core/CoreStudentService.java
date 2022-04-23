package com.example.uni.Services.Core;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CoreStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CoreCollegeService coreCollegeService;


    /**
     * function for add new student
     * @param student input data
     * @return student
     */
    public Student addStudent(Student student,Long collegeId)throws Exception{


        if (searchByNationalCode(student.getNationalCode())!=null )
            throw new Exception("The student With This national code Exist");
        else if (searchByPersonnelNumber(student.getPersonnelNumber())!=null)
            throw new Exception("The student With This Personnel Number  Exist");

        coreCollegeService.getCollege(collegeId).addStudent(student);
        studentRepository.save(student);

        return student;
    }

    /**
     * function for update student
     * @param student input data
     * @param studentId      of student
     * @return student
     */
    public Student updateStudent(Student student , Long studentId ,Long collegeId)throws Exception {

        //todo
        Student newstudent = getStudent(studentId);

        if (searchByNationalCode(student.getNationalCode())!=null)
            if (!searchByNationalCode(student.getNationalCode()).getId().equals(studentId))
                 throw new Exception("The student With This national code Exist");
        if (searchByPersonnelNumber(student.getPersonnelNumber())!=null)
            if (!searchByPersonnelNumber(student.getPersonnelNumber()).getId().equals(studentId))
                throw new Exception("The student With This Personnel Number  Exist");

        newstudent.getCollege().deleteStudent(newstudent);


        student.setId(studentId);

        coreCollegeService.getCollege(collegeId).addStudent(student);
        studentRepository.save(student);

        return student;
    }


    /**
     * function for delete student
     * @param id input data
     * @return student
     */
    public void deleteStudent (Long id) throws Exception{

        Student student= getStudent(id);

        student.getCollege().deleteStudent(student);

        studentRepository.deleteById(id);

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
     * @param id of student
     * @return  all lesson
     */
    public Set<StudentLesson> getAllStudentLesson(Long id)throws Exception{

        return getStudent(id).getStudentLessons();
    }

    /**
     * function for get avg of student
     * @param id of student
     * @return avg
     */
    public float getAvg(Long id)throws Exception{
        final float[] avg = {0};
        final int[] unit = {0};

        getAllStudentLesson(id).forEach((temp)->{

                avg[0] = temp.getGrade() * temp.getLesson().getUnit();
                unit[0] = temp.getLesson().getUnit();

        });

        if (unit[0]!=0)
            avg[0]/=unit[0];
        return avg[0];
    }

}
