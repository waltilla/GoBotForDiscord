package com.bot.gobot.service;

import com.bot.gobot.listneners.EventListener;
import com.bot.gobot.listneners.MessageListener;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReturnGameService extends MessageListener implements EventListener<MessageCreateEvent> {

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processMessage(event.getMessage());
    }
}