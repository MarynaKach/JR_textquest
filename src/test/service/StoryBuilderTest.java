package test.service;

import main.com.javarush.textquest.enteties.Message;
import main.com.javarush.textquest.service.StoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class StoryBuilderTest {
    StoryBuilder storyBuilder = new StoryBuilder();
    @Test
    public void getMessagesNotNull() {
        Set<Message> expected = storyBuilder.getMessages();
        Assert.assertNotNull(expected);
    }
    @Test
    public void buildStoryNotNull() {
        Set<Message> expected = storyBuilder.buildStory();
        Assert.assertNotNull(expected);
    }
    @Test
    public void checkSize() {
        storyBuilder.buildStory();
        Set<Message> expected = storyBuilder.getMessages();
        int expectedSize = 6;
        assertEquals(expectedSize, expected.size());
    }
}

