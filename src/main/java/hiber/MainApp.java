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
      User aUser1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("Porsche",45);
      aUser1.setCar(car1);
      userService.add(aUser1);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      Car car2 = new Car("Mercedes",77);
      User aUser3 = new User("User3", "Lastname3", "user3@mail.ru");
      aUser3.setCar(car2);
      userService.add(aUser3);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              new Car("Audi",56)));

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      User user = userService.getUserByCar("Mercedes",77);
      System.out.println("user="+user);

      User userNF = userService.getUserByCar("dfgdfgdf",77);
      System.out.println("not found user="+userNF);
     /* for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }*/

      context.close();
   }
}
