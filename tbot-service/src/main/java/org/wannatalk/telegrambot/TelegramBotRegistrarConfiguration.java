package org.wannatalk.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.WebhookBot;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TelegramBotRegistrarConfiguration {

    private final List<WebhookBot> webhookBots;
    private final List<LongPollingBot> longPollingBots;

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiRequestException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        for (WebhookBot bot : webhookBots) {
            log.info("Registering bot {}", bot.getBotUsername());
            telegramBotsApi.registerBot(bot);
        }
        for (LongPollingBot bot : longPollingBots) {
            log.info("Registering bot {}", bot.getBotUsername());
            telegramBotsApi.registerBot(bot);
        }
        return telegramBotsApi;
    }


}
