package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Anton", "Antonov", "user1@mail.ru");
        User user2 = new User("Ivan", "Ivanov", "user2@gmail.com");


        Car car1 = new Car("ВАЗ", 2115);
        Car car2 = new Car("ВАЗ", 2111);


        user1.setCar(car1);
        user2.setCar(car2);
        userService.add(user1);
        userService.add(user2);
        System.out.println(userService.getUserByCar("ВАЗ", 2115));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }



        context.close();
    }
}
