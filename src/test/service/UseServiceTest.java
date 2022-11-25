package test.service;

import main.com.javarush.textquest.enteties.User;
import main.com.javarush.textquest.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UseServiceTest {
  UserService userService = new UserService();
    private final String ID = UUID.randomUUID().toString();
    @Test
    public void saveUser() {
      User user = User
              .builder()
              .name("AnyUser")
              .build();
      userService.saveUser(ID, user);
    assertEquals(user, userService.getAllUsersList().get(ID));
    }
  @Test
  public void isUserExist() {
    User user = User
            .builder()
            .name("AnyUser")
            .build();
    userService.saveUser(ID, user);
    assertTrue(userService.isUserExist(ID));
  }

}
