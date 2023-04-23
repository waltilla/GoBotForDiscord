package com.bot.gobot.go;

import com.bot.gobot.go.logic.UpdateKifu;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Game {

    Kifu kifu;
    String lastPlayerToPutAMove;
    boolean firstMove = true;
    List<Player> players;

    public Game() {
        players = new ArrayList<>();
        this.kifu = new Kifu();
    }

    public Game(String gameFromJson) {
        loadStartedGame(gameFromJson);
    }

    private void loadStartedGame(String gameFromJson) {
        this.kifu = new Gson().fromJson(gameFromJson, Kifu.class);
        firstMove = false;
        players = kifu.listOfStones.stream().map(s -> new Player(s.getPlayer(), s.color)).distinct().toList();
        lastPlayerToPutAMove = kifu.getListOfStones().get(kifu.listOfStones.size() -1).player;
    }

    public void undo(){
        players.forEach(s -> {
            if(!s.equals(lastPlayerToPutAMove)){
                lastPlayerToPutAMove = s.getPlayer();
            }
        });
        getKifu().getListOfStones().remove(getKifu().listOfStones.size()-1);
    }



    public void addMove(String playerMakingMove, String playedPosition) {
        if (firstMove) {
            firstMove = false;
            players.add(new Player(playerMakingMove, "black"));
            kifu.addMove(moveToStone(players.get(0), playedPosition));
            lastPlayerToPutAMove = playerMakingMove;
        }

        if (players.size() == 1 && playerMakingMove != players.get(0).getPlayer()) {
            players.add(new Player(playerMakingMove, "white"));
            kifu.addMove(moveToStone(players.get(1), playedPosition));
            lastPlayerToPutAMove = playerMakingMove;
        }else if (!Objects.equals(lastPlayerToPutAMove, playerMakingMove)){
            Player player = players.stream().filter(s -> s.player.equals(playerMakingMove)).findAny().get();
            Stone stone = moveToStone(player, playedPosition);
            kifu = UpdateKifu.removeDeadStones(kifu, player.getColor(), stone.positionX, stone.positionY);
            kifu.addMove(stone);
            lastPlayerToPutAMove = player.getPlayer();
        }
        printKifu();
    }

    public Stone moveToStone(Player player, String move) {
        String[] strArray = move.split("-");
        return new Stone(player.getPlayer(),
                player.getColor(),
                Integer.parseInt(strArray[0]) - 1,
                Integer.parseInt(strArray[1]) - 1);
    }

    public void printKifu(){
       System.out.println(new Gson().toJson(kifu, Kifu.class));
    }
}
