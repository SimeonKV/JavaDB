import enteties.Instructor;
import enteties.InstructorDetail;

import javax.persistence.*;
import java.util.List;

public class Main {
   public static final EntityManagerFactory entityManagerFactory =  Persistence
            .createEntityManagerFactory("oneToOne");
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =  Persistence
                .createEntityManagerFactory("oneToOne");

      //  createInstructor("Georgi","Georgiev","georgiev@yahoo.com",
      //          "www.music.com.bg","writing music");

      //  createInstructor("Dimitar","Dimitrov","dimitrov@yahoo.com",
       //         "www.blogging.com.com","writting blogs");

      //  deleteInstructorById(2);
      //  getInstructorByInstructorDetailId();
         deleteInstructorDetailById(1L);


    }

    private static void deleteInstructorDetailById(long inputId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from InstructorDetail where id =:insId")
                    .setParameter("insId", inputId);

            InstructorDetail instructorDetail = (InstructorDetail) query.getSingleResult();
            Instructor instructor = instructorDetail.getInstructor();
            instructor.setInstructorDetail(null);
            entityManager.remove(instructorDetail);
            entityManager.getTransaction().commit();
        }catch (NoResultException nre){
            System.out.println("No such entity in the database");
        }finally {
            entityManager.close();
        }
    }

    private static void getInstructorByInstructorDetailId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        InstructorDetail instructorDetail = entityManager.getReference(InstructorDetail.class,1L);
        System.out.println(instructorDetail.getInstructor());
    }

    private static void deleteInstructorById(long instructorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

      try {

          entityManager.getTransaction().begin();
          Instructor instructor = (Instructor) entityManager
                  .createQuery("from Instructor where id =:insid", Instructor.class)
                  .setParameter("insid", instructorId)
                  .getSingleResult();

          entityManager.remove(instructor);
          entityManager.getTransaction().commit();
          entityManager.close();


      }catch (NoResultException nre){
          System.out.println("Id does not exist in the database!");
      }
    }


    private static void createInstructor(String firstName, String lastName,
                                         String email, String channel, String hobby) {
        Instructor instructor = new Instructor(firstName,lastName,email);
        InstructorDetail instructorDetail = new InstructorDetail(channel,hobby);
        instructor.setInstructorDetail(instructorDetail);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        entityManager.getTransaction().begin();

        entityManager.persist(instructor);

        entityManager.getTransaction().commit();
    }finally {
        entityManager.close();
    }


    }
}
