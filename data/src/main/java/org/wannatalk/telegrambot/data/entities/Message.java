package org.wannatalk.telegrambot.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("messages")
public class Message {

    @Id
    private String id;

    private LocalDateTime when;
    private Chat chatId;
    private Direction direction;
    private String whoId;
}
