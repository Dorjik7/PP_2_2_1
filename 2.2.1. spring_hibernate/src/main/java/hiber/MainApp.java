package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Adam", "First", "adam@eden.com", new Car("Ford", 2008));
        userService.add(user1);
        User user2 = new User("Eva", "Second", "eva@eden.com", new Car("Kia", 2001));
        userService.add(user2);
        User user3 = new User("Lilith", "Zero", "lilith@eden.com", new Car("Lada", 6));
        userService.add(user3);
        User user4 = new User("Snake", "One", "snake@eden.com", new Car("Zaporozhets", 1));
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }
        System.err.println("Показать владельца Kia 2001 года выпуска: ");
        List<User> carUsers1 = userService.userByCar("Kia", 2001);
        for (User user : carUsers1) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.err.println("Показать владельца Kia 2002 года выпуска: ");
        List<User> carUsers2 = userService.userByCar("Kia", 2002);
        if (!carUsers2.isEmpty()) {
            for (User user : carUsers2) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println("Car = " + user.getCar());
                System.out.println();
            }
        } else System.err.println("Нет такого пользователя.");

        context.close();
    }
}
