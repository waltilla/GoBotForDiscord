package com.bot.gobot.logicJohan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GoLogic {

    private List<LogicStone> stonesToBeRemoved = new ArrayList<>();

    // Its "b",        4,      4
    public String[][] run(String[][] goban, String placesStoneColor, int x, int y) {

        //place the stone on the goban
        goban[x][y] = placesStoneColor;
        // Check four neighbours and see if they are the opposite color.
        List<LogicStone> opponentsStones = check4ClosestNeighboursIfItsAnOpponentStone(goban, placesStoneColor, x, y);

        // check if the four neighbours and its potential group is alive.
        // in the test its a black stone placed "b"
        for (LogicStone whiteStone : opponentsStones) {

            // empty global list with stones to be removed before each check
            stonesToBeRemoved.clear();
            // check if the stone and its neighbours is alive
            boolean remove = checkIfStonesNeighboursHasAnyLiberties(goban, whiteStone.getColor(), whiteStone.getPositionX(), whiteStone.getPositionY());
            // remove stones from the board
            if (!remove) {
                removeDeadStones(goban);
            }
        }

        testIfCorrect(getGobanAfterWhiteStonesIsRemoved(), goban);
        printGoban(goban);

        return goban;
    }

    public boolean checkIfStonesNeighboursHasAnyLiberties(String[][] goban, String stoneColor, int x, int y) {
        // Method used to be able to break the recursive method its calling.
        try {
            checkFourNeightbours(goban, stoneColor, x, y);
            // if it reaches here, its probably dead stones...
            return false;
        } catch (StoneHasLibertiesBreakSearchException e) {
            return true;
        }
    }

    public boolean checkFourNeightbours(String[][] goban, String stoneColor, int x, int y) {
        stonesToBeRemoved.add(new LogicStone(stoneColor, x, y));
        checkNeighbour(goban, stoneColor, x + 1, y); // to right
        checkNeighbour(goban, stoneColor, x - 1, y); // to left
        checkNeighbour(goban, stoneColor, x, y + 1); // to on top
        checkNeighbour(goban, stoneColor, x, y - 1); // to on under
        return true;
    }

    private void checkNeighbour(String[][] goban, String stoneColor, int x, int y) {
        // Is it an empty intersection? break
        // If not
        // Look if it is a friendly stone, then recurseive the method
        // If
        // its a opponent stone remove one libertie.

        // if its a edge stone, catch exception
        try {
            String stoneToRight = goban[x][y];
            if (stoneToRight.equals(".")) {
                throw new StoneHasLibertiesBreakSearchException("yes!");
            }
            if (stoneToRight.equals(stoneColor)) {
                stonesToBeRemoved.add(new LogicStone(stoneColor, x, y));
                checkFourNeightbours(goban, stoneColor, x, y);
            }
        } catch (IndexOutOfBoundsException e) {
            // Oh corner position.
        }
    }

    // Return the four closest neighbours if their of the opposite color
    private List<LogicStone> check4ClosestNeighboursIfItsAnOpponentStone(String[][] goban, String placedStoneColor, int x, int y) {
        List<LogicStone> opponentStones = new ArrayList<>();
        // check stone to right is an opponent stone, add it
        if (Objects.equals(getPositionAsStringCheckEdge(goban, x + 1, y), getOpponentColor(placedStoneColor))) {
            opponentStones.add(new LogicStone(getOpponentColor(placedStoneColor), x + 1, y));
        }
        // check stone to left is an opponent stone, add it
        if (Objects.equals(getPositionAsStringCheckEdge(goban, x - 1, y), getOpponentColor(placedStoneColor))) {
            opponentStones.add(new LogicStone(getOpponentColor(placedStoneColor), x - 1, y));
        }
        // check stone above is an opponent stone, add it
        if (Objects.equals(getPositionAsStringCheckEdge(goban, x, y + 1), getOpponentColor(placedStoneColor))) {
            opponentStones.add(new LogicStone(getOpponentColor(placedStoneColor), x, y + 1));
        }
        // check stone under is an opponent stone, add it
        if (Objects.equals(getPositionAsStringCheckEdge(goban, x, y - 1), getOpponentColor(placedStoneColor))) {
            opponentStones.add(new LogicStone(getOpponentColor(placedStoneColor), x, y - 1));
        }
        return opponentStones;
    }
    public String getOpponentColor(String color) {
        switch (color) {
            case "b" -> color = "w";
            case "w" -> color = "s;";
            case "." -> color = ".";
            default -> color = "error";
        }
        return color;
    }

    private String[][] removeDeadStones(String[][] goban) {
        stonesToBeRemoved.forEach(stoneToBeRemoved ->
            goban[stoneToBeRemoved.getPositionX()][stoneToBeRemoved.getPositionY()] = ".");
        return goban;
    }

    private String getPositionAsStringCheckEdge(String[][] goban, int x, int y) {
        String stoneColor;
        try {
            stoneColor = goban[x][y];
        } catch (IndexOutOfBoundsException e) {
            stoneColor = "edge"; //
        }
        return stoneColor;
    }







    //TODO: REMOVE THIS ONLY USED FOR TESTING THIS METHOD.............................................................
    private void testIfCorrect(String[][] gobanCapturedTwoWhiteStone, String[][] goban) {
        if (Arrays.deepEquals(getGobanAfterWhiteStonesIsRemoved(), goban)) {
            System.out.println("Sebbe youre the master! ");
        } else {
            System.out.println("Still not right");
        }
    }

    private void printGoban(String[][] goban) {
        for (String[] strings : goban) {
            for (int ind = 0; ind < strings.length; ind++) {
                System.out.print(strings[ind] + " ");
            }
            System.out.println();
        }
    }

    private String[][] getGobanAfterWhiteStonesIsRemoved() {
        String[][] gobanCaptureTwoWhiteStone = {
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", "b", "b", "b", "b", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", "b", ".", ".", "b", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", "b", "b", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
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
        return gobanCaptureTwoWhiteStone;
    }

    public class LogicStone {

        public String color;
        public int positionX;
        public int positionY;

        public  LogicStone(String color, int positionX, int positionY) {
            this.color = color;
            this.positionX = positionX;
            this.positionY = positionY;
        }

        public int getPositionX() {
            return positionX;
        }
        public int getPositionY() {
            return positionY;
        }
        public String getColor(){ return color;}
    }

}
