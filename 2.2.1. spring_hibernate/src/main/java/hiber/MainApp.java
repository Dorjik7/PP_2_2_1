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
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Adam", "First", "adam@eden.com");
        User user2 = new User("Eva", "Second", "eva@eden.com");
        User user3 = new User("Lilith", "Zero", "lilith@eden.com");
        User user4 = new User("Snake", "One", "snake@eden.com");

        Car car1 = new Car("Ford", 2008);
        Car car2 = new Car("Kia", 2001);
        Car car3 = new Car("Lada", 6);
        Car car4 = new Car("Zaporozhets", 1);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel());
            }
        System.out.println("Показать владельца машины Kia: ");
        System.out.println(userService.getByCar("Kia", 2001));
        context.close();
    }
}
