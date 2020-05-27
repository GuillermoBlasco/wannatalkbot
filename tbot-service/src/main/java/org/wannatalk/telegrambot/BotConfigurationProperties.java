package org.wannatalk.telegrambot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bot")
@Getter
@Setter
public class BotConfigurationProperties {
    public String name;
    public String username;
    public String apiKey;
    public int maxLinksToSend = 5;
}
