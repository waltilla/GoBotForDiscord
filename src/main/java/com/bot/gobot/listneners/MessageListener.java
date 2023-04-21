package com.bot.gobot.listneners;

import com.bot.gobot.go.Game;
import com.bot.gobot.go.Goban;
import com.bot.gobot.img.CreatePixelArrayFromKifu;
import com.bot.gobot.img.ImageFromIntArray;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateSpec;
import reactor.core.publisher.Mono;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MessageListener {

    Game game;
    String info = "";

    public MessageListener() {
        game = new Game();
    }

    public Mono<Void> processMessage(final Message eventMessage) {
        info = "";
        List<String> listOfUsers = Arrays.asList("Timmyonetoe", "BooGrim");



        InputStream targetStream = null;
        String filePathToGobanToPrint = System.getProperty("java.io.tmpdir") + "Output.png";
        System.out.println(filePathToGobanToPrint);
        try {
            targetStream =
                    new DataInputStream(new FileInputStream(filePathToGobanToPrint));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStream finalTargetStream = targetStream;




        if(listOfUsers.stream().filter( s -> s.equals(eventMessage.getAuthor().get().getUsername())).toList().size() == 0 ||
                eventMessage.getData().content().charAt(0) != '&'){
            return Mono.empty().then();
        }






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

                            else if (messageFromChat.equals("new")) {
                                game = new Game();
                            }

                            else if (messageFromChat.equals("undo")) {
                                game.undo();
                            } else {
                                try {
                                    game.addMove(eventMessage.getAuthor().get().getUsername(), messageFromChat);
                                } catch (Exception e) {
                                    System.out.println("prutt");
                                }
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
                                .addFile(filePathToGobanToPrint, finalTargetStream)
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
