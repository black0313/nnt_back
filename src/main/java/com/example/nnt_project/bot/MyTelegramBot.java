package com.example.nnt_project.bot;


import com.example.nnt_project.entity.DispatchersTeam;
import com.example.nnt_project.repository.DispatchersTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {
    @Autowired
    DispatchersTeamRepository dispatchersTeamRepository;

    @Override
    public String getBotUsername() {
        return "appnntBot"; // Bot username ni kiriting
    }

    @Override
    public String getBotToken() {
        return "7054615642:AAGXOlJBsD0Ta5ij4MhhJ3kEa0OfX4DsDcM"; // Bot token ni kiriting
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMyChatMember()) {
            ChatMemberUpdated myChatMember = update.getMyChatMember();
            long chatId = myChatMember.getChat().getId();
            String chatTitle = myChatMember.getChat().getTitle();

            // Agar bot yangi qo'shilgan bo'lsa
            if (myChatMember.getNewChatMember().getUser().getId().equals(getBotId())) {
                // Bot guruhga qo'shilganida chat ID sini tekshirish
                if (!dispatchersTeamRepository.existsByGroupId(chatId)) {
                    sendMessageToGroup(chatId, "Guruh chat ID: " + chatId,"HTML");

                    // Chat ID ni saqlash uchun yangi DispatchersTeam ob'ektini yaratish
                    DispatchersTeam newGroup = new DispatchersTeam();
                    newGroup.setName(chatTitle);
                    newGroup.setGroupId(chatId);
                    dispatchersTeamRepository.save(newGroup);
                }
            }
        }
    }

    // Xabar yuborish uchun yordamchi metod
    public void sendMessageToGroup(Long chatId, String messageText,String format ) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(messageText);
        message.setParseMode(format);
        message.enableHtml(true);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private Long getBotId() {
        try {
            return execute(new GetMe()).getId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
