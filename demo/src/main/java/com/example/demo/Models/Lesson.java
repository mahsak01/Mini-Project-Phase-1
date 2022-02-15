package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1 ,max = 255)
    private String name;

    @NotNull
    @Min(1)
    private int unit;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long uniId;

    protected Lesson() {}


    public Lesson(String name, int unit, Long teacherId, Long uniId) {
        this.name = name;
        this.unit = unit;
        this.teacherId = teacherId;
        this.uniId = uniId;
    }

    @Override
    public String toString() {
        return String.format(
                "Lesson[id=%d, name='%s', unit='%s', TeacherId='%s', uniId=%d]",
                id, name,unit,teacherId,uniId);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getUnit() {
        return unit;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getUniId() {
        return uniId;
    }

    public Long getId() {
        return id;
    }
}
