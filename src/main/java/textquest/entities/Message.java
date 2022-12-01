package textquest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class Message {
    private int id;
    private String textMessage;
    private Set<Answer> set;
    private int pictureId;
}
