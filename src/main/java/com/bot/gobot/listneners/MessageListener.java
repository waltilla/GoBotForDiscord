package com.bot.gobot.listneners;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public abstract class MessageListener {



    public Mono<Void> processMessage(final Message eventMessage){

        List<String> listOfOkUsers = Arrays.asList("Timmyonetoe", "BooGrim");

        return Mono.just(eventMessage)
                .filter(message -> {
                    final Boolean canPlay = message.getAuthor()
                            .map(user -> !user.isBot())
                            .orElse(false);

                    if(canPlay){
                        message.getAuthor().ifPresent(listOfOkUsers::contains);
                    }
                    return canPlay;
                })
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Ja dig kan man lita på!"))
                .then();

        // Skriv om för att ta in message och sedan skicka ut bilden.
    }



}
