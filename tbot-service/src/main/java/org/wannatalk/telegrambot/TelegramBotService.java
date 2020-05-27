package org.wannatalk.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.wannatalk.telegrambot.data.entities.Link;
import org.wannatalk.telegrambot.service.chat.ChatService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(BotConfigurationProperties.class)
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramBotInitialiser telegramBotInitialiser;
    private final BotConfigurationProperties properties;
    private final ChatService chatService;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String text = update.getMessage().getText();
            String userId = String.valueOf(update.getMessage().getFrom().getId());
            if (isUrl(text)) {
                chatService.newLink(text, userId);
                execute(message("Stored!", update.getMessage().getChatId()));
            } else {
                List<Link> link = chatService.findLink(text, userId);
                execute(message("Found " + link.size() + " links", update.getMessage().getChatId()));
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage message(String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }

    private boolean isUrl(String text) {
        try {
            new URL(text);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public String getBotToken() {
        return properties.getApiKey();
    }

}
