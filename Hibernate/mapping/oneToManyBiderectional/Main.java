import enteties.Course;
import enteties.Instructor;
import enteties.InstructorDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
   public static final EntityManagerFactory entityManagerFactory =  Persistence
            .createEntityManagerFactory("oneToOne");
    public static void main(String[] args) {

   //     createInstructorWithCourses("Simo","Simov","English","Italian","Spanish");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Instructor instructor = entityManager
                .getReference(Instructor.class,4l);

        System.out.println(instructor);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    private static void createInstructorWithCourses(String fName, String lName
            , String courseOne, String courseTwo, String courseThree) {
        Instructor instructor = new Instructor(fName,lName,null);
        Course firstCourse = new Course(courseOne);
        Course secondCourse = new Course(courseTwo);
        Course thirdCourse = new Course(courseThree);
        List<Course> courses = new ArrayList<>();
        courses.add(firstCourse);
        courses.add(secondCourse);
        courses.add(thirdCourse);
        instructor.setCourses(courses);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(instructor);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
