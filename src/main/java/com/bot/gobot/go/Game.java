package com.bot.gobot.go;

public class Game {

    Kifu kifu;
    String lastPlayerToPutAMove;
    boolean firstMove = true;
    String black = null;
    String white = null;

    public Game() {
        this.kifu = new Kifu();
    }

    public void addMove(String player, String color, String move) {
        if (firstMove) {
            black = player;
            firstMove = false;
            kifu.addMove(moveToStone(player, color, move));
        }
        if (kifu.getKifu().size() == 1 && player != black) {
            white = player;
        }

        if (lastPlayerToPutAMove != player) {
            kifu.addMove(moveToStone(player, color, move));
        }
    }


    public Stone moveToStone(String player, String color, String move) {
        String[] split = move.split("-");
        return new Stone(player, color, Integer.getInteger(split[0]), Integer.getInteger(split[1]));
    }


}
