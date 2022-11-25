package main.com.javarush.textquest.enteties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private Integer numberOfGames;
}
