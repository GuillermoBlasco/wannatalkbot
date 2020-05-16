package org.wannatalk.telegrambot.data.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Direction {
    USER_TO_BOT, BOT_TO_USER;
}
