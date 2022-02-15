package com.example.demo.Models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Sec {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long secId;

    @NotNull
    private Long lessonId;

    @NotNull
    @JoinColumn(name="student_id", referencedColumnName="id" ,table = "Student")
    private Long studentId;

    @NotNull
    private Long teacherId;

    @NotNull
    @Min(0)
    @Max(20)
    private float score;

    @NotNull
    @Min(1)
    private int term;

    protected Sec(){}

    public Sec(Long secId, Long lessonId, Long studentId, Long teacherId, float score, int term) {
        this.secId = secId;
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.score = score;
        this.term = term;
    }

    @Override
    public String toString() {
        return String.format(
                "Sec[id=%d, secId=%d, lessonId=%d, studentId=%d, teacherId=%d, score=%f, term=%d]",
                id, secId,lessonId,studentId,teacherId,score,term);
    }

    public Long getSecId() {
        return secId;
    }

    public void setSecId(Long secId) {
        this.secId = secId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
