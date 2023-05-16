package jpamvcexam.model.dao;

import jpamvcexam.model.vo.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDAO {

    private EntityManagerFactory factory;

    public StudentDAO() {
        super();
        factory = Persistence.createEntityManagerFactory("emptest");
    }

    public boolean insertStudent(Student entity) {
        boolean result = true;
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        em.close();
        return result;
    }

    public List<Student> getAllStudent() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Student> q = em.createQuery("select s from Student s", Student.class);
        List<Student> list = q.getResultList();
        em.close();
        return list;
    }

    public Student getScore(String name) {
        EntityManager em = factory.createEntityManager();
        Student vo = em.find(Student.class, name);
        em.close();
        return vo;
    }

    public boolean updateStudent(Student entity) {
        boolean result = true;
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Student oldVO = em.find(Student.class, entity.getName());
            oldVO.setScore(entity.getScore());
            em.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        em.close();
        return result;
    }

    public boolean deleteStudent(String name) {
        boolean result = true;
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Student vo = em.find(Student.class, name);
            em.remove(vo);
            em.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        em.close();
        return result;
    }

    public void close() {
        if(factory != null) {
            factory.close();
        }
    }

}
