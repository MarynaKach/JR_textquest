package main.com.javarush.textquest.enteties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private String answerText;
    private int nextMessageId;
}
