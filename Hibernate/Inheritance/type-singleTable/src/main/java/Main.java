import com.sun.jdi.connect.Transport;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = new Student(12,"","");
        Teacher teacher = new Teacher("","",3);

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
