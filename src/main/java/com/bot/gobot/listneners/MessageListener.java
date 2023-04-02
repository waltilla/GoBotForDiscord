package com.bot.gobot.listneners;

import com.bot.gobot.go.Game;
import com.bot.gobot.go.Goban;
import com.bot.gobot.img.CreatePixelArrayFromKifu;
import com.bot.gobot.img.ImageFromIntArray;
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

    Game game;
    String info = "";

    public MessageListener() {
        game = new Game();
    }

    public Mono<Void> processMessage(final Message eventMessage) {
        info = "";
        List<String> listOfUsers = Arrays.asList("Timmyonetoe", "BooGrim", "Vickypod", "Bullenjullen");
        InputStream targetStream = null;

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
                        String messageFromPlayer = eventMessage.getContent();
                        if (messageFromPlayer.charAt(0) != '&' && eventMessage.getData().channelId().asString().equals("1075151561401061386")) {
                            return false;
                        }

                        // The bot responds "only" if its starts with &
                        if (messageFromPlayer.charAt(0) == '&') {

                            String messageFromChat = messageFromPlayer.substring(1);

                            if (messageFromChat.equals("info")) {
                                info = info();
                            }

                            if (messageFromChat.equals("new")) {
                                game = new Game();
                            }

                            if (messageFromChat.equals("undo")) {
                                game.undo();
                            }

                            try {
                                game.addMove(eventMessage.getAuthor().get().getUsername(), messageFromChat);
                            } catch (Exception e) {
                                System.out.println("prutt");
                            }

                            ImageFromIntArray.generate(
                                    CreatePixelArrayFromKifu.
                                            createImageOfGobanFromKifu(Goban.getCleanGoban(), game.getKifu()));

                        }
                    }


                    return canPlay;
                })
                .flatMap(Message::getChannel)
                .flatMap(channel ->


                        channel.createMessage(MessageCreateSpec.builder()
                                .content("Your prompt: " + eventMessage.getContent() + "\n" + info)
                                .addFile("src/main/resources/Output.png", finalTargetStream)
                                .build()))
                .then();
        // Skriv om för att ta in message och sedan skicka ut bilden.
    }

    public String info() {
        return  """
                You have the following options: 
                &new == new game
                &12-12 == putting a stone on position 12x12
                &undo == undo last move
                
                thats it. Have fun! 
                                
                """;
    }


}
