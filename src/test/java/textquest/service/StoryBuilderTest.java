package textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import textquest.entities.Message;
import textquest.enums.MessageNumbers;
import textquest.servlets.AppServlet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StoryBuilderTest {
    StoryBuilder storyBuilder = new StoryBuilder();
    MessageNumbers messageNumbers;
    AppServlet appServlet = new AppServlet();
    @BeforeEach
    public void setup() {
        appServlet.init();
    }
    @Test
    public void getMessagesNotNull() {
        Set<Message> expected = storyBuilder.getMessages();
        assertNotNull(expected);
    }
    @Test
    public void buildStoryNotNull() {
        Set<Message> expected = storyBuilder.buildStory(messageNumbers);
        assertNotNull(expected);
    }
    @Test
    public void checkSize() {
        storyBuilder.buildStory(messageNumbers);
        Set<Message> expected = storyBuilder.getMessages();
        int expectedSize = 6;
        assertEquals(expectedSize, expected.size());
    }
}
