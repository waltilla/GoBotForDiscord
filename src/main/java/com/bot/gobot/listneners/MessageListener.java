package com.bot.gobot.listneners;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateSpec;
import reactor.core.publisher.Mono;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public abstract class MessageListener {

    public Mono<Void> processMessage(final Message eventMessage) {

        List<String> listOfUsers = Arrays.asList("Timmyonetoe", "BooGrim");
        InputStream targetStream = null;


        String content = eventMessage.getContent();

        try {
            targetStream =
                    new DataInputStream(new FileInputStream("src/main/resources/Output.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStream finalTargetStream = targetStream;

        return Mono.just(eventMessage)
                .filter(message -> {
                    final Boolean canPlay = message.getAuthor()
                            .map(user -> !user.isBot())
                            .orElse(false);

                    System.out.println(message.getData().content());

                    if (canPlay) {
                        message.getAuthor().ifPresent(listOfUsers::contains);
                    }


                    return canPlay;
                })

                .flatMap(Message::getChannel)
                .flatMap(channel ->



                    channel.createMessage(MessageCreateSpec.builder()
                        .content("Your move: "   + content )
                        .addFile("src/main/resources/Output.png", finalTargetStream)
                        .build()))
                .then();

        // Skriv om för att ta in message och sedan skicka ut bilden.
    }


}
