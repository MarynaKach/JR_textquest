package test.enteties;

import main.com.javarush.textquest.enteties.User;
import org.junit.Test;
import org.mockito.Mockito;

public class UserTest {
    User userMock = Mockito.mock(User.class) ;

    @Test
    public void getAnswerText() {
        userMock.getName();
        Mockito.verify(userMock).getName();
    }
}
