import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {
   static Configuration configuration = new Configuration().configure();
 static    SessionFactory sessionFactory = configuration.buildSessionFactory();
 static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        insetUserIntoDB();
    }

    private static void insetUserIntoDB() {
        System.out.println("Creating a user...");
        System.out.println("!!!For a successful creation of a user all fields must be filled out!!!");
        System.out.println("___________________________________________________________________");

        while (true){
            System.out.println("Enter first name: ");
            String firstName = SCANNER.nextLine();
            System.out.println("Enter last name: ");
            String lastName = SCANNER.nextLine();
            System.out.println("Enter home street: ");
            String homeStreet = SCANNER.nextLine();
            System.out.println("Enter home town");
            String homeTown = SCANNER.nextLine();
            System.out.println("Enter another street: ");
            String anotherStreet = SCANNER.nextLine();
            System.out.println("Enter another town: ");
            String anotherTown = SCANNER.nextLine();

            User user = createUser(firstName,lastName,homeStreet,homeTown,anotherStreet,anotherTown);
            databaseInsertion(user);

            System.out.println("Would you like to create another user: [Yes/No]");
            String choice = SCANNER.nextLine();
            if(choice.equalsIgnoreCase("no")){
                return;
            }
        }
    }

    private static User createUser(String firstName, String lastName, String homeStreet, String homeTown, String anotherStreet, String anotherTown) {
        Address homeAddress = new Address();
        homeAddress.setStreet(homeStreet);
        homeAddress.setTown(homeTown);

        Address extraAddress = new Address();
        extraAddress.setStreet(anotherStreet);
        extraAddress.setTown(anotherTown);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(homeAddress);
        user.setExtraAddress(extraAddress);

        return user;
    }

    private static void databaseInsertion(User user) {
//        Configuration configuration = new Configuration().configure();
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
