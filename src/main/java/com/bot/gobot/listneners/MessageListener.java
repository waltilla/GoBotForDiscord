package com.bot.gobot.listneners;

import com.bot.gobot.config.AllowedPlayersConfig;
import com.bot.gobot.go.Game;
import com.bot.gobot.go.Goban;
import com.bot.gobot.go.Player;
import com.bot.gobot.go.Stone;
import com.bot.gobot.img.CreatePixelArrayFromKifu;
import com.bot.gobot.img.ImageFromIntArray;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageCreateSpec;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class MessageListener {

    Game game;
    String messageToPlayer = "";

    @Autowired
    AllowedPlayersConfig allowedPlayers;

    public MessageListener() {
        game = new Game();
    }

    public Mono<Void> processMessage(final Message eventMessage) {

        // Check if message is from author in allowed list of players in application.yml
        if (allowedPlayers.getPlayers().stream().filter(s -> s.equals(eventMessage.getAuthor().get().getUsername())).toList().size() == 1
                && eventMessage.getData().content().charAt(0) == '&') {
            return playAMove(eventMessage);
        }
        return Mono.empty();
    }

    private Mono<Void> playAMove(Message eventMessage) {

        String filePathToGobanToPrint = System.getProperty("java.io.tmpdir") + "Output.png";
        InputStream finalTargetStream = targetStream(filePathToGobanToPrint);

        return Mono.just(eventMessage)
                .filter(message -> {
                        var messageFromChat = eventMessage.getContent().substring(1);
                        if (messageFromChat.equals("info")) {
                            messageToPlayer = info();
                        } else if (messageFromChat.equals("new")) {
                            game = new Game();
                        } else if (messageFromChat.equals("undo")) {
                            game.undo();
                        } else {
                            makeMove(eventMessage);
                        }
                    return true;
                })
                .flatMap(Message::getChannel)
                .flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                                .content(messageToPlayer)
                                .addFile(filePathToGobanToPrint, finalTargetStream)
                                .build()))
                .then();
    }

    private void makeMove(Message eventMessage) {
        // Remove &
        String messageFromChat = eventMessage.getContent().substring(1);

        if(validMove(messageFromChat)){
            try {
                game.addMove(eventMessage.getAuthor().get().getUsername(), messageFromChat);
                messageToPlayer = messageFromChat;
                ImageFromIntArray.generate(
                        CreatePixelArrayFromKifu.
                                createImageOfGobanFromKifu(Goban.getCleanGoban(), game.getKifu()));
            } catch (Exception e) {
                System.out.println("Error...");
            }
        }
    }

    public boolean validMove(String move){
        String[] strArray = move.split("-");
        return Integer.parseInt(strArray[0]) >= 1 && Integer.parseInt(strArray[0]) <= 19 &&
                Integer.parseInt(strArray[1]) >= 1 && Integer.parseInt(strArray[1]) <= 19;
    }

    private InputStream targetStream(String Path) {

        try {
            return new DataInputStream(new FileInputStream(Path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String info() {
        return """
                You have the following options: 
                &new == new game
                &12-12 == putting a stone on position 12x12
                &undo == undo last move
                                
                thats it. Have fun! 
                                
                """;
    }



}
