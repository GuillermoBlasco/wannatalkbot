package org.wannatalk.telegrambot.data.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.List;

import static org.wannatalk.telegrambot.data.config.MongoDbConfigurationProperties.BASE_PACKAGE_REPOS;

@Configuration
@EnableMongoRepositories(basePackages={BASE_PACKAGE_REPOS})
@EnableConfigurationProperties(MongoDbConfigurationProperties.class)
@RequiredArgsConstructor
public class MongoDbConfig extends AbstractMongoClientConfiguration {

    private final MongoDbConfigurationProperties properties;

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create(properties.getUrl());
    }

    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return List.of(BASE_PACKAGE_REPOS);
    }

}
