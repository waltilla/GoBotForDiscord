package com.bot.gobot.img;

public class CreatePixelArrayFromKifu {
/*

    String[][] goban = {
            {"tl", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "tr"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
            {"bl", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "br"}
    };*/

    public static int[][] createBigArrayFromGobanAndPictures() {

  /*      String[][] goban = {
                {"y", "å", "å","å"},
                {"å", "y", "å","å"},
                {"å", "y", "å","å"},
                {"å", "y", "å","å"}
        };*/

        String[][] goban = {
                {"tl", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "t", "tr"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", "b", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", "w", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "w"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "r"},
                {"l", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "b"},
                {"bl", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "v", "br"}
        };
        int lenghtOfSinlgeTile = get2dArrayofPictureFromString(goban[0][0]).length;


        int[][] bigPicture = new int
                [goban.length * lenghtOfSinlgeTile]
                [goban.length * lenghtOfSinlgeTile];

        int count = 0;
        for (int row = 0; row < goban.length; row++) {
            for (int col = 0; col < goban.length; col++) {

                String s = goban[row][col];
                int[][] singlePosition = get2dArrayofPictureFromString(s);

                for (int i = 0; i < singlePosition.length; i++) {
                    for (int j = 0; j < singlePosition.length; ++j) {
                        count++;
                        int x = (row * lenghtOfSinlgeTile) + i;
                        int y = (col * lenghtOfSinlgeTile) + j;
                        bigPicture[x][y] = singlePosition[i][j];

                    }
                }

            }
        }

        return bigPicture;

    }

    public static int[][] get2dArrayofPictureFromString(String boardPositin) {
        return switch (boardPositin) {
            case "b" -> new ImgParts().getBlackstone();
            case "w" -> new ImgParts().getWhitestone();
            case "tr" -> new ImgParts().getUpperRight();
            case "tl" -> new ImgParts().getUpperLeft();
            case "bl" -> new ImgParts().getLowerLeft();
            case "br" -> new ImgParts().getLowerRight();
            case "v" -> new ImgParts().getBottom();
            case "t" -> new ImgParts().getTop();
            case "l" -> new ImgParts().getLeft();
            case "r" -> new ImgParts().getRight();
            case "." -> new ImgParts().getIntersection();
            case "y" -> new ImgParts().getDebug2();
            case "å" -> new ImgParts().getDebug3();
            default -> throw new IllegalStateException("Dumbass... ");
        };
    }

}