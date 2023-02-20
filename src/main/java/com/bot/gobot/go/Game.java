package com.bot.gobot.go;

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
            kifu.addMove(moveToStone(player, playedPosition));
            lastPlayerToPutAMove = player.getPlayer();
        }
    }

    public Stone moveToStone(Player player, String move) {
        String[] strArray = move.split("-");
        return new Stone(player.getPlayer(),
                player.getColor(),
                Integer.parseInt(strArray[0]) - 1,
                Integer.parseInt(strArray[1]) - 1);
    }
}
