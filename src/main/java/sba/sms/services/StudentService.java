package sba.sms.services;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.util.List;

public class StudentService implements StudentI {

    CourseService courseService = new CourseService();

    @Override
    public List<Student> getAllStudents() {
        List<Student> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Student> q = session.createQuery("from  Student", Student.class);

            result = q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void createStudent(Student student) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();

        }

    }

    @Override
    public Student getStudentByEmail(String email) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            TypedQuery<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            Student student=query.getSingleResult();
            return student;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();

        }
        return null;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            TypedQuery<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            Student student=query.getSingleResult();
            if(student.getPassword().equals(password))
                return true;
        } catch (HibernateException ex) {

            ex.printStackTrace();
        } finally {
            session.close();

        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {
        Student student = getStudentByEmail(email);
        Course course = courseService.getCourseById(courseId);

        if(!student.getCourses().contains(course)){
            student.getCourses().add(course);
        }
    }

    @Override
    public List<Course> getStudentCourses(String email) {
        Student student = getStudentByEmail(email);
        return student.getCourses();
    }

}