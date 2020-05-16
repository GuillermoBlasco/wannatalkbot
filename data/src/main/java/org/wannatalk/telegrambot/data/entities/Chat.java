package org.wannatalk.telegrambot.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("chats")
public class Chat {

    @Id
    private String id;

    private LocalDateTime startTimestamp;
    private Channel channel;
    private String channelChatId;
    private String whoId;
}
