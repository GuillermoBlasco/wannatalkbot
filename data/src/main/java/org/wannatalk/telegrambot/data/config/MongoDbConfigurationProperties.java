package org.wannatalk.telegrambot.data.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mongodb")
@Getter @Setter
public class MongoDbConfigurationProperties {

    private String url = "mongodb://localhost:27027";
    private String database = "wanna_talk";

    static final String BASE_PACKAGE_REPOS = "org.wannatalk.telegrambot.data.entities";

}
