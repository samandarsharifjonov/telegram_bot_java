package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import token_and_username.BotUserName;
import java.util.HashSet;

public class MyBot extends TelegramLongPollingBot {
    BotUserName botUserName = new BotUserName();
    private HashSet<Integer> userIds = new HashSet<>();
    int userCounter = 0;

    @Override
    public void onUpdateReceived(Update update) {

        String userInfo = "";
        Message message = new Message();

        if(update.getMessage().getText().equals("/help") || update.getMessage().getText().equals("/Help")){
            userInfo+="\nWhat kind of help do you need?\nWrite to admin\nAdmin: @SamandarbekSharifjonov";
        }else {
            if (update.getMessage().getForwardFrom() != null && message.getText() == null ) {
                final Long forwardFromUserId =  update.getMessage().getForwardFrom().getId();
                final String forwardFirstName = update.getMessage().getForwardFrom().getFirstName();
                final String forwardLastName =  update.getMessage().getForwardFrom().getLastName();
                final String forwardUserName =  update.getMessage().getForwardFrom().getUserName();

                userInfo+="Information about the user of the forwarded message \n";
                userInfo += "\nUserID: "+forwardFromUserId+"\n";
                userInfo += "First name: "+forwardFirstName;

                if(forwardLastName != null){
                    userInfo += "\nLast name: "+forwardLastName;
                }else {
                    userInfo += "\nLast name:  ⚠\uFE0F No last name";
                }

                if(forwardUserName  != null){
                    userInfo += "\nUser name: @"+forwardUserName;
                }else {
                    userInfo += "\nUser name: ⚠\uFE0F username not found";

                }
                userInfo +="\n\n\uD83D\uDCCA Number of bot users  " +userCounter;
                userInfo +="\n\uD83C\uDD98 Command for help /help";
            }else {
                if(update.hasMessage() && update.getMessage().getText() != null && update.getMessage().getForwardFrom() == null){
                    final String firstName = update.getMessage().getFrom().getFirstName();
                    final String lastName = update.getMessage().getFrom().getLastName();
                    final String username = update.getMessage().getFrom().getUserName();
                    long userId = update.getMessage().getFrom().getId();
                    long chatId = update.getMessage().getChatId();
                    if( userIds.add(((int) userId))){
                        userCounter++;
                    }
                    userInfo+="User info\n";
                    userInfo += "\nUserID: "+userId;
                    userInfo+= "\nChatID: "+chatId;
                    userInfo += "\nFirst name: "+firstName;

                    if(lastName != null){
                        userInfo += "\nLast name: "+lastName;
                    }else {
                        userInfo += "\nLast name:  ⚠\uFE0F No last name";
                    }

                    if(username  != null){
                        userInfo += "\nUser name: @"+username;
                    }else {
                        userInfo += "\nUser name: ⚠\uFE0F username not found";

                    }
                    userInfo +="\n\n\uD83D\uDCCA Number of bot users  " +userCounter;
                    userInfo +="\n\uD83C\uDD98 Command for help /help";
                }
            }

        }

        SendMessage sendMessage = new SendMessage();
        long userId = update.getMessage().getFrom().getId();
        long chatId = update.getMessage().getChatId();



        sendMessage.setChatId(chatId);
        sendMessage.setChatId(userId);
        sendMessage.setText(userInfo);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getBotUsername() {
        return botUserName.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botUserName.getBotToken();
    }
}