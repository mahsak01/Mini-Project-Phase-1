package com.example.uni.Services;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.College;
import com.example.uni.Models.Models;
import com.example.uni.Models.Person;
import com.example.uni.Models.Student;
import com.example.uni.Repositories.CollegeRepository;
import com.example.uni.Repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CollegeRepository collegeRepository;

    @Mock
    private CollegeService collegeService;

    @InjectMocks
    private StudentService studentService;

    @Before
    public void addCollege() throws Exception {
        College college = new College("math");
        collegeService.addCollege(college);
    }


    @Test
     public void getAllStudent(){
        studentService.getAllStudent();
        verify(studentRepository).findAll();
    }

    @Test
    public void getStudentIsNotExist(){
        Exception exception = assertThrows(Exception.class, () -> studentService.getStudent(-1L));

        assertEquals("The student With This id not Exist", exception.getMessage());
    }
//
//    @Test
//    public void addStudentNotExist() throws Exception {
//
//        College college = new College("math");
//        collegeService.addCollege(college);
//
//        PersonDto studentDto = new PersonDto(
//                "98237232",
//                "mahsa2",
//                "karimi2",
//                "0440973730",
//                (Long) Models.getField(college,"id")
//        );
//
//
//        studentService.addStudent(studentDto);
//
//        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
//
//        verify(studentRepository).save(captor.capture());
//
//        assertEquals(captor.getValue().getFirstname(), studentDto.getFirstname());
//
//    }

//    @Test
//    public void getStudentExist() throws Exception {
//
//        Student student = new Student(
//                "98237232",
//                "mahsa2",
//                "karimi2",
//                "0440973730"
//        );
//
//        studentRepository.save(student);
//
//         assertEquals(student,studentService.getStudent(student.getId()));
//
//    }

}