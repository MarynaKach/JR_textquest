package test.enteties;

import main.com.javarush.textquest.enteties.Answer;
import org.junit.Test;
import org.mockito.Mockito;


public class AnswerTest {
    Answer answerMock = Mockito.mock(Answer.class) ;

    @Test
    public void getAnswerText() {
        answerMock.getAnswerText();
        Mockito.verify(answerMock).getAnswerText();
    }

    @Test
    public void getNextId() {
        answerMock.getNextMessageId();
        Mockito.verify(answerMock).getNextMessageId();
    }
}
