package com.example.uni.Services;
import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.*;
import com.example.uni.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param studentDto input data
     * @return student
     */
    public Student addStudent(PersonDto studentDto)throws Exception{


        if (searchByNationalCode((String) Models.getField(studentDto,"nationalCode"))!=null )
            throw new Exception("The student With This national code Exist");
        else if (searchByPersonnelNumber((String) Models.getField(studentDto,"personnelNumber"))!=null)
            throw new Exception("The student With This Personnel Number  Exist");



        Student student= new Student((String) Models.getField(studentDto,"personnelNumber"),
                (String) Models.getField(studentDto,"firstname"),
                (String) Models.getField(studentDto,"lastname"),
                (String) Models.getField(studentDto,"nationalCode"));



        collegeService.getCollege((Long) Models.getField(studentDto,"collegeId")).addStudent(student);
        studentRepository.save(student);

        return student;
    }

    /**
     * function for update student
     * @param studentDto input data
     * @param studentId      of student
     * @return student
     */
    public Student updateStudent(PersonDto studentDto , Long studentId )throws Exception {

        //todo
        Student student = getStudent(studentId);

        if (searchByNationalCode((String) Models.getField(studentDto,"nationalCode"))!=null)
            if (!Models.getField(searchByNationalCode((String) Models.getField(studentDto,"nationalCode")),"id").equals(studentId))
                 throw new Exception("The student With This national code Exist");
        if (searchByPersonnelNumber((String) Models.getField(studentDto,"personnelNumber"))!=null)
            if (!Models.getField(searchByPersonnelNumber((String) Models.getField(studentDto,"nationalCode")),"id").equals(studentId))
                throw new Exception("The student With This Personnel Number  Exist");

        ((College)Models.getField(student,"college")).deleteStudent(student);

        student = new Student((String) Models.getField(studentDto,"personnelNumber"),
                (String) Models.getField(studentDto,"firstname"),
                (String) Models.getField(studentDto,"lastname"),
                (String) Models.getField(studentDto,"nationalCode"));

        Models.setField(student,"id",studentId);

        collegeService.getCollege((Long) Models.getField(studentDto,"collegeId")).addStudent(student);
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

        ((College) Models.getField(student,"college")).deleteStudent(student);

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

        return (Set<StudentLesson>) Models.getField(getStudent(id),"studentLessons");
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


            try {
                avg[0] = (float) Models.getField(temp,"grade") * (int)Models.getField(Models.getField(temp,"lesson"),"unit");
                unit[0] = (int) Models.getField(Models.getField(temp,"lesson"),"unit");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (unit[0]!=0)
            avg[0]/=unit[0];
        return avg[0];
    }

}
