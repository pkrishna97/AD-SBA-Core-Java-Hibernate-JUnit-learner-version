package sba.sms.services;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.util.List;

public class StudentService implements StudentI {

    @Override
    public List<sba.sms.models.Student> getAllStudents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            List<sba.sms.models.Student> list = session.createQuery("from student", sba.sms.models.Student.class).getResultList();
            return list;
        } catch (HibernateException ex) {

            ex.printStackTrace();
        } finally {
            session.close();

        }
        return null;

    }

    @Override
    public void createStudent(sba.sms.models.Student student) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            // transient mode

            // persist mode
            session.persist(student);


            tx.commit();
        } catch (HibernateException ex) {

            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();

        }

    }

    @Override
    public sba.sms.models.Student getStudentByEmail(String email) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            List<sba.sms.models.Student> list = session.createQuery("from student where email="+email, sba.sms.models.Student.class).getResultList();
            return list.get(0);
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
            List<sba.sms.models.Student> list = session.createQuery("from student where email="+email, sba.sms.models.Student.class).getResultList();
            sba.sms.models.Student student= list.get(0);
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


    }

    @Override
    public List<Course> getStudentCourses(String email) {

        return null;
    }

}
