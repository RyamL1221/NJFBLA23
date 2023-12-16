package FBLAProject.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.io.Serializable;

@Table(name = "student")
@Entity
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "gradeLevel")
    private int gradeLevel;
    @Column(name = "points")
    private int points;
    @Column(name = "eventsAttended")
    private String eventsAttended = "";

    @Column(name = "code")
    private String code;

    public Student() {}

    public Student(String name, int gradeLevel, int points) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(String eventsAttended) {
        this.eventsAttended = eventsAttended;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student: {" + "\tId: " + id + "\tName: " + name + "\tGrade Level: " + gradeLevel + "\tEvents Attended: " + "eventsAttended" + "\tPoints: " + points + "\tCode:" + code;
    }
}
