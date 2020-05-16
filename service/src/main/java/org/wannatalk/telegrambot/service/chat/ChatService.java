package org.wannatalk.telegrambot.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wannatalk.telegrambot.data.entities.*;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public void newMessage(NewIncomingMessage incomingMessage) {
        String chatId = incomingMessage.composeChatId();
        Chat chat = chatRepository.findById(chatId).orElseGet(() -> createChat(incomingMessage));
        Message message = createMessage(chat, incomingMessage);
    }

    private Message createMessage(Chat chat, NewIncomingMessage newIncomingMessage) {
        Message message = new Message();
        message.setDirection(Direction.USER_TO_BOT);
        //message.setId();
        return messageRepository.save(message);
    }

    private Chat createChat(NewIncomingMessage message) {
        Chat chat = new Chat();
        chat.setChannel(message.getChannel());
        chat.setChannelChatId(message.getChannelChatId());
        chat.setId(message.composeChatId());
        chat.setWhoId(message.composeWhoId());
        chat.setStartTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

}
