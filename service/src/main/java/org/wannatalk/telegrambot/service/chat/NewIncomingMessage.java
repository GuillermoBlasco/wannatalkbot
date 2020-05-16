package org.wannatalk.telegrambot.service.chat;

import lombok.Builder;
import lombok.Value;
import org.wannatalk.telegrambot.data.entities.Channel;

@Value
@Builder
public class NewIncomingMessage {
    Channel channel;
    String channelChatId;
    long timestamp;
    String message;
    String who;

    public String composeWhoId() {
        return channel.getKey() + "-" + who;
    }
    public String composeChatId() {
        return channel.getKey() + "-" + channelChatId;
    }
}
