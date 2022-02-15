package com.example.demo.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Uni {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2 , max = 255)
    private String uniName;

    protected Uni() {}

    public Uni( String uniName) {
        this.uniName = uniName;
    }

    @Override
    public String toString() {
        return String.format(
                "Uni[id=%d, uniName='%s']",
                id, uniName);
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
