package textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.http.HttpSession;
import textquest.entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UseServiceTest {

  UserService userService;
  private String id;
  User user;
  @Mock
  HttpSession session;
  private Map<String, User> allUsersList;
  @BeforeEach
  public void setUp() {

    id = UUID.randomUUID().toString();
    user = User
            .builder()
            .name("AnyUser")
            .numberOfGames(1)
            .build();
    allUsersList = new HashMap();
    allUsersList.put("AnyUser", user);
    userService = new UserService(allUsersList, user);
  }
  @Test
  public void saveUserIdNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(null, user));
  }
  @Test
  public void saveUserIfNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(id, null));
  }
  @Test
  public void saveUserAllNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.saveUser(null, null));
  }
    @Test
    public void saveUser() {
    userService.saveUser(id, user);
    assertEquals(user, userService.getAllUsersList().get(id));
    }
  @Test
  public void isUserExistNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.isUserExist(null));
  }
  @Test
  public void isUserExist() {
    userService.saveUser(id, user);
    assertTrue(userService.isUserExist(id));
  }
  @Test
  public void checkUserNameNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.checkUser(null, session));
  }
  @Test
  public void checkUserNameSessionNull() {
    assertThrows(IllegalArgumentException.class,
            () -> userService.checkUser(null, null));
  }

  @Test
  public void checkExistedUser() {
    userService.checkUser("AnyUser", session);
    assertEquals(1, allUsersList.size());
  }
  @Test
  public void checkNewUser() {
    userService.checkUser("NewUser", session);
    assertEquals(2, allUsersList.size());
  }
}
