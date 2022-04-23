package com.example.uni.Services.Crud;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.ProfessorRepository;
import com.example.uni.Services.Core.CoreProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CrudProfessorService {

    @Autowired
    private CoreProfessorService coreProfessorService;


    /**
     * function for add new professor
     *
     * @param professorDto input data
     * @return professor
     */
    public Professor addProfessor(PersonDto professorDto) throws Exception {

        Professor professor = new Professor(professorDto.getPersonnelNumber(), professorDto.getFirstname(),
                professorDto.getLastname(),professorDto.getNationalCode());
        return coreProfessorService.addProfessor(professorDto);
    }

    /**
     * function for update professor
     *
     * @param professorDto   input data
     * @param professorId of professor
     * @return professor
     */
    public Professor updateProfsser(PersonDto professorDto, Long professorId) throws Exception {

        Professor professor = new Professor(professorDto.getPersonnelNumber(), professorDto.getFirstname(),
                professorDto.getLastname(),professorDto.getNationalCode());
        return coreProfessorService.updateProfsser(professorDto,professorId);
    }

    /**
     * function for delete professor
     *
     * @param professorId input data
     * @return professor
     */
    public void deleteProfsser(Long professorId) throws Exception {

        coreProfessorService.deleteProfsser(professorId);

    }

    /**
     * function for get all professor
     *
     * @return all professor
     */
    public List<Professor> getAllProfessor() {
        return coreProfessorService.getAllProfessor();
    }

    /**
     * function for get professor
     *
     * @param professorId input data
     * @return professor
     */
    public Professor getProfessor(Long professorId) throws Exception {
        return coreProfessorService.getProfessor(professorId);
    }

    /**
     * function for get all lesson of college
     *
     * @param professorId of professor
     * @return all lesson
     */
    public Set<Lesson> getAllLesson(Long professorId) throws Exception {

        return coreProfessorService.getAllLesson(professorId);
    }

    /**
     * function for get all student of professor
     *
     * @param professorId of professor
     * @return list of student
     */
    public List<Student> getAllStudent(Long professorId) throws Exception {
        return coreProfessorService.getAllStudent(professorId);
    }

    /**
     * function for add lesson of professor
     *
     * @param professorId input data
     * @param lessonId    input data
     * @return professor
     */

    public Professor addLesson(Long professorId, Long lessonId) throws Exception {
        return coreProfessorService.addLesson(professorId,lessonId);
    }


    /**
     * function for delete lesson of professor
     *
     * @param professorId input data
     * @param lessonId    input data
     * @return professor
     */
    public Professor deleteLesson(Long professorId, Long lessonId) throws Exception {
        return coreProfessorService.deleteLesson(professorId, lessonId);
    }







}
