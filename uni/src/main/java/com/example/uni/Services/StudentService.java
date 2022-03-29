package com.example.uni.Services;
import com.example.uni.Dto.LessonDto;
import com.example.uni.Dto.StudentDto;
import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
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
    public Student addStudent(StudentDto studentDto)throws Exception{

        if (searchByNationalCode(studentDto.getNationalCode())!=null )
            throw new Exception("The student With This national code Exist");
        else if (searchByPersonnelNumber(studentDto.getPersonnelNumber())!=null)
            throw new Exception("The student With This Personnel Number  Exist");

        Student student= new Student(studentDto.getPersonnelNumber(),
                studentDto.getFirstname(),studentDto.getLastname(),
                studentDto.getNationalCode());


        collegeService.getCollege(studentDto.getCollegeId()).addStudent(student);
        studentRepository.save(student);

        return student;
    }

    /**
     * function for update student
     * @param studentDto input data
     * @param studentId      of student
     * @return student
     */
    public Student updateStudent(StudentDto studentDto , Long studentId )throws Exception {

        //todo
        Student student = getStudent(studentId);

        if (searchByNationalCode(studentDto.getNationalCode())!=null)
            if (!searchByNationalCode(studentDto.getNationalCode()).getId().equals(studentId))
                 throw new Exception("The student With This national code Exist");
        if (searchByPersonnelNumber(studentDto.getPersonnelNumber())!=null)
            if (!searchByPersonnelNumber(studentDto.getNationalCode()).getId().equals(studentId))
                throw new Exception("The student With This Personnel Number  Exist");

        student.getCollege().deleteStudent(student);

        student = new Student(studentDto.getPersonnelNumber(),
                studentDto.getFirstname(), studentDto.getLastname(),
                studentDto.getNationalCode());
        student.setId(studentId);

        collegeService.getCollege(studentDto.getCollegeId()).addStudent(student);
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
