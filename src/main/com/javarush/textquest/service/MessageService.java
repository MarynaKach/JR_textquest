package main.com.javarush.textquest.service;

import main.com.javarush.textquest.enteties.Answer;
import main.com.javarush.textquest.enteties.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    public String getLink(String answer, int id, StoryBuilder storyBuilder) {
        String hidden;
        int nextIdYes;
        int nextIdNo;
        String link;
        if (id == 4 || id == 0) {
            if (answer.equalsIgnoreCase("yes")) {
                link = "index.jsp";
            } else {
                link = "/quest?messageId=5";
            }
        }else {
            if (answer.equalsIgnoreCase("yes")) {
                nextIdYes = getNextId(id, "yes", storyBuilder);
                link = "/quest?messageId=" + nextIdYes;
            } else {
                nextIdNo = getNextId(id, "no", storyBuilder);
                link = "/quest?messageId=" + nextIdNo;
            }
        }
        LOGGER.info("Link for " + answer + " bottom has benn built");
        return link;
    }

    public int getPictureById(int id, StoryBuilder storyBuilder) {
        int pictureId = 0;
        Message currentMessage;
        for (Message message : storyBuilder.getMessages()) {
            if (message.getId() == id) {
                pictureId = message.getPictureId();
            }
        }
        return pictureId;
    }

    public int getMessageId(HttpServletRequest request) {
        int messageId = Integer.parseInt(request.getParameter("messageId"));
        //boolean isNumeric = message.chars().allMatch(Character::isDigit);
        return messageId; /*isNumeric ? Integer.parseInt(message) : 0;*/
    }
    public String getMessageById(int id, StoryBuilder storyBuilder) {
        String textMessage = null;
        for (Message message : storyBuilder.getMessages()) {
            if (message.getId() == id) {
                textMessage = message.getTextMessage();
            }
        }
        LOGGER.info("The message with " + id + " id has been returned");
        return textMessage;
    }
    public int getNextId (int id, String textAnswer, StoryBuilder storyBuilder) {
        Message message = null;
        for (Message message1 : storyBuilder.getMessages()) {
            if(message1.getId() == id) {
                message = message1;
            }
        }
        Answer answer = null;
        Set<Answer> set = message.getSet();
        int nextId;
        if (set == null) {
            nextId = 0;
        } else {
            for (Answer answer1 : set/*message.getSet()*/) {
                if (textAnswer.equalsIgnoreCase(answer1.getAnswerText())) {
                    answer = answer1;
                }
            }
            nextId = answer.getNextMessageId();
        }
        LOGGER.info("The id for the next message " + id + " was returned");
        return nextId;
    }

    public String getNameFromRequest(HttpServletRequest req) {
        String name = req.getParameter("name");
        return name;
    }
}