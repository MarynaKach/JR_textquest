package textquest.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import textquest.entities.Answer;
import textquest.entities.Message;
import textquest.enums.MessageNumbers;
import textquest.enums.MessageParameters;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoryBuilder {

    private static final Logger LOGGER = Logger.getLogger(StoryBuilder.class);
    private Set<Message> messages = new HashSet<>();

    public Set<Message> buildStory(MessageNumbers messageNumbers) {
        for (MessageNumbers messageNumber : messageNumbers.values()) {
            Message message = creatMessage(messageNumber);
            messages.add(message);
        }
        return messages;
    }

    private Message creatMessage(MessageNumbers messageNumber) {
        Message message;
        int id = Integer.parseInt(PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.MESSAGE_ID), 10);
        String textMessage = PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.TEXT_MESSAGE);
        Set<Answer> answersSet = creatAnswerSet(messageNumber);
        if (answersSet == null) {
            message = Message.builder()
                    .id(id)
                    .textMessage(textMessage)
                    .pictureId(id)
                    .build();
        } else {
            message = Message.builder()
                    .id(id)
                    .textMessage(textMessage)
                    .pictureId(id)
                    .set(answersSet)
                    .build();
        }
        return message;
    }

    private Set<Answer> creatAnswerSet(MessageNumbers messageNumber) {
        int nextYesMessageId = Integer.parseInt(PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.NEXT_YES_ANSWER_ID), 10);
        int nextNoMessageId = Integer.parseInt(PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.NEXT_NO_ANSWER_ID), 10);
        String answerNoText = PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.NO_ANSWER);
        String answerYesText = PropertiesLoader
                .getMessageProperties(messageNumber, MessageParameters.YES_ANSWER);

        Set<Answer> set = new HashSet<>();
        if (answerYesText.equalsIgnoreCase("last")) {
            return null;
        } else {
            set.add(Answer.builder()
                    .answerText(answerYesText)
                    .nextMessageId(nextYesMessageId)
                    .build());
            set.add(Answer.builder()
                    .answerText(answerNoText)
                    .nextMessageId(nextNoMessageId)
                    .build());
        }
        return set;
    }
}
