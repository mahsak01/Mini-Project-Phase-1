package com.example.uni.Services;

import com.example.uni.Models.College;
import com.example.uni.Models.Models;
import com.example.uni.Repositories.CollegeRepository;
import com.example.uni.Repositories.LessonRepository;
import com.example.uni.Repositories.ProfessorRepository;
import com.example.uni.Repositories.StudentRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class CollegeServiceTest {

    @Mock
    private CollegeRepository collegeRepository;
    @Mock
    private ProfessorRepository professorRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private CollegeService collegeService;


    @Test
    public void getAllCollege(){
        collegeService.getAllCollege();
        verify(collegeRepository).findAll();
    }



    @Test
    public void addCollegeNotExist() throws Exception {
        College college = new College("math");
        collegeService.addCollege(college);
        ArgumentCaptor<College> captor = ArgumentCaptor.forClass(College.class);

        verify(collegeRepository).save(captor.capture());


        assertEquals(Models.getField(captor.getValue(),"collegeName"), Models.getField(college,"collegeName"));

    }

    ///failed
    @Test
    public void addCollegeExist()  {
        College college = new College("math");

        collegeRepository.save(college);

        Exception exception = assertThrows(Exception.class, () -> collegeService.addCollege(college));

        assertEquals("The college With This name Exist", exception.getMessage());

    }

    @Test
    public void getCollegeExist() throws Exception {

        given(collegeRepository.findById(anyLong())).willReturn(java.util.Optional.of(new College()));

        collegeService.getCollege(1L);
        verify(collegeRepository, atLeast(1)).findById(1L);
    }

    @Test
    public void getCollegeNotExist() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> collegeService.getCollege(1L));


        assertEquals("The college With This id not Exist", exception.getMessage());

    }


}
