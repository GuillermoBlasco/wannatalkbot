package org.wannatalk.telegrambot.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("link")
public class Link {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private String url;
    private String title;
    private String description;
    private String textSearch;
    private String userId;

}
