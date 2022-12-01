package textquest.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import textquest.entities.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private Map<String, User> allUsersList = new HashMap();
    User user;

    public void saveUser(String id, User user) {
        if (id == null) {
            throw new IllegalArgumentException("User's id can't be null.");
        }
        if (user == null) {
            throw new IllegalArgumentException("User can't be null.");
        }
        allUsersList.put(id, user);
        LOGGER.info("The User with such name " + id + " was saved.");
    }

    public boolean isUserExist(String id) {
        if (id == null) {
            throw new IllegalArgumentException("User's id can't be null.");
        }
        LOGGER.info("The User with such name " + id + " is exist: " + allUsersList.containsKey(id));
        return allUsersList.containsKey(id);
    }

    public void checkUser(String name, HttpSession session) {
        if (name == null) {
            throw new IllegalArgumentException("User's name can't be null.");
        }
        if (!isUserExist(name)) {
            user = User.builder()
                    .name(name)
                    .numberOfGames(1)
                    .build();
            saveUser(name, user);
            session.setAttribute("numberOfGames", user.getNumberOfGames());

        } else {
            user = allUsersList.get(name);
            user.setNumberOfGames(user.getNumberOfGames() + 1);
            session.setAttribute("numberOfGames", user.getNumberOfGames());
        }
        LOGGER.info("Check if the User with such name " + name + " is exist. ");
    }
}
