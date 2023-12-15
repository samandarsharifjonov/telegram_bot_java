package token_and_username;

public class BotUserName {
    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

     private String botToken = "Your bot token";
     private String  botUserName = "your bot username";

}
