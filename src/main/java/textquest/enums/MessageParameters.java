package textquest.enums;

public enum MessageParameters {
    TEXT_MESSAGE("textMessage"),
    MESSAGE_ID("messageId"),
    YES_ANSWER("yesAnswer"),
    NO_ANSWER("noAnswer"),
    NEXT_YES_ANSWER_ID("nextYesAnswerId"),
    NEXT_NO_ANSWER_ID("nextNoAnswerId");
    private String name;

    MessageParameters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
