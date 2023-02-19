package com.bot.gobot.go;

public class Goban {

    public static String[][] getCleanGoban() {
        String[][] goban = {
                {"tl", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "tr"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", ".", ".", ".", "x", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"bl", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "br"}
        };
        return goban;
    }
}
