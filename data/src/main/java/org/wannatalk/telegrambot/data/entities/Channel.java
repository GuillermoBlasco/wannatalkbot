package org.wannatalk.telegrambot.data.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Channel {
    TELEGRAM("telegram");
    private final String key;
}
