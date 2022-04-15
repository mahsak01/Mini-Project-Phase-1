package com.example.uni.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "professor")
public class Professor extends Person{

    @Id
    @SequenceGenerator(
            name = "professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "professor_sequence"
    )
    private Long id;


    @ManyToMany(mappedBy = "professors",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Lesson> lessons ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private College college;


    public Professor() {

    }

    public Professor(String personnelNumber, String firstname, String lastname, String nationalCode) {
        this.personnelNumber = personnelNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
    }



    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", personnelNumber=" + personnelNumber +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
