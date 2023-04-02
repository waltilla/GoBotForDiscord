package com.bot.gobot.go.logic;

import com.bot.gobot.go.Kifu;
import com.bot.gobot.go.Stone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateKifu {


    public static Kifu removeDeadStones(Kifu kifu, String color, int positionX, int positionY) {

        String[][] gobanWithPositionsFromGameKifu = updateGobanWithPositionsFromKifu(cleanGoban(), kifu);
        var removeDeadStonesKifu = new RemoveDeadStones().check(gobanWithPositionsFromGameKifu,
                nameToChar(color),
                positionX,
                positionY);

        return updateKifuWithKifuNotContainRemovedStones(kifu, removeDeadStonesKifu);

    }

    private static Kifu updateKifuWithKifuNotContainRemovedStones(Kifu kifu, String[][] removeDeadStonesKifu) {
        List<Stone> stones = kifu.getListOfStones().stream().filter(stone -> {

            String positionOnTheGoban = removeDeadStonesKifu[stone.positionX ][stone.positionY];

            if (nameToChar(stone.getColor()).equals(positionOnTheGoban)) {
                return true;
            } else {
                return false;
            }
        }).toList();
        ArrayList<Stone> a1 = new ArrayList<Stone>(stones);
        kifu.setListOfStones(a1);
        return kifu;
    }

    public static String[][] updateGobanWithPositionsFromKifu(String[][] goban, Kifu kifu) {
        kifu.getListOfStones().forEach(stone -> goban[stone.positionX][stone.positionY] = nameToChar(stone.getColor()));
        return goban;
    }

    public static String nameToChar(String stone) {
        if (stone.equals("black")) {
            return "b";
        } else {
            return "w";
        }
    }

    private static String[][] cleanGoban() {
        String[][] goban = {
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."}};
        return goban;
    }
}
