import entities.ContractEmployee;
import entities.RegularEmployee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("company");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ContractEmployee contractEmployee = new ContractEmployee("Jhon",12.32);
        RegularEmployee regularEmployee = new RegularEmployee("Bill",500,23);


        entityManager.getTransaction().begin();
        entityManager.persist(contractEmployee);
        entityManager.persist(regularEmployee);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
