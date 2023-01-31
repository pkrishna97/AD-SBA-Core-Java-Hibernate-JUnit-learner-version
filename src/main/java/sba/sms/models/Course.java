package sba.sms.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "Course")
@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="name", length=50, nullable = false)
    private String name;
    @Column(name="instructor", length=50, nullable = false)
    private String instructor;
    @ManyToMany(mappedBy = "courses",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Student> students;

    public Course() {
    }

    public Course(int id, String name, String instructor, List<Student> students) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.students = students;

    }

    public Course(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() && getName().equals(course.getName()) && getInstructor().equals(course.getInstructor()) && Objects.equals(getStudents(), course.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInstructor(), getStudents());
    }
}
