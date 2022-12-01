package textquest.service;

import org.apache.log4j.Logger;

import textquest.entities.Answer;
import textquest.entities.Message;

import java.util.Set;

public class MessageService {
    private static final Logger LOGGER = Logger.getLogger(MessageService.class);

    public String getLink(String answer, int id, StoryBuilder storyBuilder) {
        if (answer == null || id < 0 || storyBuilder == null) {
            throw new IllegalArgumentException("Answer can't be null.");
        }
        int nextIdYes;
        int nextIdNo;
        String link;
        if (id == 4 || id == 0) {
            if (answer.equalsIgnoreCase("yes")) {
                link = "index.jsp";
            } else {
                link = "/quest?messageId=5";
            }
        } else {
            if (answer.equalsIgnoreCase("yes")) {
                nextIdYes = getNextId(id, "yes", storyBuilder);
                link = "/quest?messageId=" + nextIdYes;
            } else {
                nextIdNo = getNextId(id, "no", storyBuilder);
                link = "/quest?messageId=" + nextIdNo;
            }
        }
        LOGGER.info("Link for " + answer + " button has been built");
        return link;
    }

    public int getIdOfPicture(int id, StoryBuilder storyBuilder) {
        int pictureId = 0;
        if (storyBuilder == null) {
            throw new IllegalArgumentException("Story is null.");
        }
        for (Message message : storyBuilder.getMessages()) {
            if (message.getId() == id) {
                pictureId = message.getPictureId();
            }
        }
        return pictureId;
    }

    public String getMessageById(int id, StoryBuilder storyBuilder) {
        String textMessage = null;
        if (storyBuilder == null) {
            throw new IllegalArgumentException("Story is null.");
        }
        for (Message message : storyBuilder.getMessages()) {
            if (message.getId() == id) {
                textMessage = message.getTextMessage();
            }
        }
        LOGGER.info("The message with " + id + " id has been returned");
        return textMessage;
    }

    public int getNextId(int id, String textAnswer, StoryBuilder storyBuilder) {
        if (storyBuilder == null || textAnswer == null) {
            throw new IllegalArgumentException("Story is null.");
        }
        Message message = null;
        for (Message message1 : storyBuilder.getMessages()) {
            if (message1.getId() == id) {
                message = message1;
            }
        }
        Answer answer = null;
        Set<Answer> set = message.getSet();
        int nextId;
        if (set == null) {
            nextId = 0;
        } else {
            for (Answer answer1 : set) {
                if (textAnswer.equalsIgnoreCase(answer1.getAnswerText())) {
                    answer = answer1;
                }
            }
            nextId = answer.getNextMessageId();
        }
        LOGGER.info("The id for the next message " + id + " was returned");
        return nextId;
    }
}
