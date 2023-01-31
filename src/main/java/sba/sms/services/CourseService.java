package sba.sms.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class CourseService {

    public void createCourse(Course course) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            tx = session.beginTransaction();
            session.merge(course);
            tx.commit();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    public Course getCourseById(int courseId) {
        Course c = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Course> q = session.createQuery("from  Course c where c.Id = :courseId", Course.class)
                    .setParameter("courseId", courseId);
            c = q.getSingleResult();

        } catch (HibernateException ex) {
            ex.printStackTrace();

        } finally {
            session.close();
        }

        return c;
    }

    public List<Course> getAllCourses() {
        List<Course> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Course> q = session.createQuery("FROM  Course", Course.class);

            result = q.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        } finally {
            session.close();
        }
        return result;
    }

}