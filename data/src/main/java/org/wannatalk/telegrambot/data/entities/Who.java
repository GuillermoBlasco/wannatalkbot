package org.wannatalk.telegrambot.data.entities;

import org.springframework.data.annotation.Id;

public class Who {

    @Id
    private String id;

    private Channel channel;
    private String name;
    private String firstName;
    private String secondName;
}
