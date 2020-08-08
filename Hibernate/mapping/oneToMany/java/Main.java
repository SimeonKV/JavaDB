import entities.Answer;
import entities.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("questions_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Answer answerOne = new Answer();
        answerOne.setAnswerName("Java is a programing lang");
        answerOne.setPostedBy("Ravi Malik");

        Answer answerTwo = new Answer();
        answerTwo.setAnswerName("Java is a platform");
        answerTwo.setPostedBy("Sudhir Kumar");

        Answer answerThree = new Answer();
        answerThree.setAnswerName("Servlet is an interface");
        answerThree.setPostedBy("Jai Kumar");

        Answer answerFour = new Answer();
        answerFour.setAnswerName("Servlet is an API");
        answerFour.setPostedBy("Arun");


        List<Answer> listOne = new ArrayList<>();
        listOne.add(answerOne);
        listOne.add(answerTwo);

        List<Answer> listTwo = new ArrayList<>();
        listTwo.add(answerThree);
        listTwo.add(answerFour);

        Question questionOne = new Question();
        questionOne.setQuestionName("What is Java?");
        questionOne.setAnswers(listOne);

        Question questionTwo = new Question();
        questionTwo.setQuestionName("What is a servlet?");
        questionTwo.setAnswers(listTwo);


        entityManager.persist(questionOne);
        entityManager.persist(questionTwo);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
