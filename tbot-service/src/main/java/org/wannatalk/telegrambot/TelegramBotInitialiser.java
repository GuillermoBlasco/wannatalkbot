package org.wannatalk.telegrambot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;

@Component
public class TelegramBotInitialiser {

    public TelegramBotInitialiser() {
        ApiContextInitializer.init();
    }

}
