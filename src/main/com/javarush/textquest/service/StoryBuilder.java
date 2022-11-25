package main.com.javarush.textquest.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import main.com.javarush.textquest.enteties.Answer;
import main.com.javarush.textquest.enteties.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class StoryBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoryBuilder.class);
    private Set<Message> messages = new HashSet<>();

    public Set<Message> buildStory() {
        messages.add(Message.builder()
                .id(0)
                .textMessage("You made wrong choice, You will die. Do you want to play again?")
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
                .textMessage("Are you ready to accept the help of aliens?")
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
                .textMessage("You accepted the help of aliens, are going to follow the captain office.")
                .set(Set.of(Answer.builder()
                                .answerText("yes")
                                .nextMessageId(3)
                                .build(),
                        Answer.builder()
                                .nextMessageId(0)
                                .answerText("no")
                                .build()))
                .build());
        messages.add(Message.builder()
                .id(3)
                .textMessage("Tell me about yourself. Are you going to tell all the trues?")
                .set(Set.of(Answer.builder()
                                .answerText("yes")
                                .nextMessageId(4)
                                .build(),
                        Answer.builder()
                                .nextMessageId(0)
                                .answerText("no")
                                .build()))
                .build());
        messages.add(Message.builder()
                .id(4)
                .textMessage("You have passed all levels, you will be sent home. You are the winner. Do you want to play again?")
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
                .id(5)
                .textMessage("THE END")
                .build());
        LOGGER.info("Story Has built.", messages.size());
        return messages;
    }

}
