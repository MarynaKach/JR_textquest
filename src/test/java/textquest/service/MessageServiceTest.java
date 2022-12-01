package textquest.service;


import textquest.entities.Answer;
import textquest.entities.Message;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    private String yesAnswer = "yes";
    private String noAnswer = "no";
    private Set<Message> messages = new HashSet<>();
    private MessageService messageService = new MessageService();
    private StoryBuilder storyBuilder;

    @BeforeEach
    public void setUp() {
        messages.add(Message.builder()
                .id(0)
                .textMessage("Test Message 0")
                .pictureId(0)
                .set(Set.of(Answer.builder()
                                .answerText("yes")
                                .nextMessageId(1)
                                .build(),
                        Answer.builder()
                                .nextMessageId(5)
                                .answerText("no")
                                .build()))
                .build());
        messages.add(Message.builder()
                .id(1)
                .textMessage("Test message 1")
                .pictureId(1)
                .set(Set.of(Answer.builder()
                                .answerText("yes")
                                .nextMessageId(2)
                                .build(),
                        Answer.builder()
                                .nextMessageId(0)
                                .answerText("no")
                                .build()))
                .build());
        messages.add(Message.builder()
                .id(2)
                .textMessage("Test Message 2")
                .pictureId(2)
                .set(Set.of(Answer.builder()
                                .answerText("yes")
                                .nextMessageId(2)
                                .build(),
                        Answer.builder()
                                .nextMessageId(0)
                                .answerText("no")
                                .build()))
                .build());
        storyBuilder = new StoryBuilder(messages);
    }
    @Test
    public void ifAnswerNull(){

        assertThrows(IllegalArgumentException.class,
                () -> messageService.getLink(null, 1, storyBuilder));
    }
    @Test
    public void ifIdNull(){
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getLink("yes", -1, storyBuilder));
    }
    @Test
    public void ifStoryBuilderNull(){
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getLink("yes", 1, null));
    }
    @Test
    public void allArgumentsIncorrect(){
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getLink(null, -1, null));
    }
    @Test
    public void getLinkIdFourYesTest() {
        assertEquals("index.jsp", messageService.getLink(yesAnswer, 4, storyBuilder));
    }
    @Test
    public void getLinkIdZeroYesTest() {
        assertEquals("index.jsp", messageService.getLink(yesAnswer, 0, storyBuilder));
    }
    @Test
    public void getLinkIdFourNoTest() {
        assertEquals("/quest?messageId=5", messageService.getLink(noAnswer, 4, storyBuilder));
    }
    @Test
    public void getLinkIdZeroNoTest() {
        assertEquals("/quest?messageId=5", messageService.getLink(noAnswer, 0, storyBuilder));
    }

    @Test
    public void getYesLinkTest() {

        assertEquals("/quest?messageId=2", messageService.getLink(yesAnswer, 1, storyBuilder));
    }
    @Test
    public void getNoLinkTest() {

        assertEquals("/quest?messageId=0", messageService.getLink(noAnswer, 1, storyBuilder));
    }
    @Test
    public void getIdOfPicture_whenStoryBuilderNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getIdOfPicture(0, null));
    }
    @Test
    public void getIdOfPicture_checkReturnedResult() {
        assertEquals(1, messageService.getIdOfPicture(1, storyBuilder));
    }
    @Test
    public void getMessageById_StoryBuilderNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getMessageById(1, null));
    }
    @Test
    public void getMessageById_checkReturnedResult() {
        assertEquals("Test message 1", messageService.getMessageById(1, storyBuilder));
    }
    @Test
    public void getNextId_StoryBuilderNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getNextId(1, "no", null));
    }

    @Test
    public void getNextId_textAnswerNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getNextId(1, null, storyBuilder));
    }
    @Test
    public void getNextId_twoArgsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageService.getNextId(1, null, null));
    }
    @Test
    public void getNextId_checkYesReturnedResult() {
        assertEquals(2, messageService.getNextId(1, "yes", storyBuilder));
    }
    @Test
    public void getNextId_checkNoReturnedResult() {
        assertEquals(0, messageService.getNextId(1, "no", storyBuilder));
    }
}
