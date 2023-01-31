package sba.sms.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table (name= "Student")
@Entity
public class Student {
    // email
    // name
    // password
    //course
    @Id
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name="student_email"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses;

    public Student() {
    }

    public Student(String email, String name, String password, List<Course> courses) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.courses = courses;
    }

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return  Objects.equals(email, other.email)
                && Objects.equals(name, other.name) && Objects.equals(password, other.password);
    }

}
