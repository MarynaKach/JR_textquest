package test.enteties;

import main.com.javarush.textquest.enteties.Message;
import org.junit.Test;
import org.mockito.Mockito;

public class MessageTest {
    Message messageMock = Mockito.mock(Message.class) ;

    @Test
    public void getTextMessage() {
        messageMock.getTextMessage();
        Mockito.verify(messageMock).getTextMessage();
    }

    @Test
    public void getNextId() {
        messageMock.getId();
        Mockito.verify(messageMock).getId();
    }
    @Test
    public void getSet() {
        messageMock.getSet();
        Mockito.verify(messageMock).getSet();
    }
}
