package main.com.javarush.textquest.service;

import lombok.Getter;
import main.com.javarush.textquest.enteties.User;
import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Getter
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private Map<String, User> allUsersList = new HashMap();
    User user;

    public void saveUser(String id, User user) {

        allUsersList.put(id, user);
        LOGGER.info("The User with such name " + id + " was saved.");
    }
    public boolean isUserExist(String id) {
        LOGGER.info("The User with such name " + id + " is exist: " + allUsersList.containsKey(id));
        return allUsersList.containsKey(id);
    }
    public void checkUser (String name, HttpSession session) {
        if (!isUserExist(name)) {
            user = User.builder()
                    .name(name)
                    .numberOfGames(1)
                    .build();
            saveUser(name, user);
            session.setAttribute("numberOfGames",  user.getNumberOfGames());

        } else {
            user = allUsersList.get(name);
            user.setNumberOfGames(user.getNumberOfGames() + 1);
            session.setAttribute("numberOfGames", user.getNumberOfGames());
        }
        LOGGER.info("Check if the User with such name " + name + " is exist. ");
    }
}
