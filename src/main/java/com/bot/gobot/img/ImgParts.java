package com.bot.gobot.img;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ImgParts {

    int[][] blackstone;
    int[][] intersection;
    int[][] lowerLeft;
    int[][] lowerRight;
    int[][] upperLeft;
    int[][] upperRight;
    int[][] whitestone;
    int[][] debug2;
    int[][] debug3;
    int[][] left;
    int[][] right;
    int[][] top;
    int[][] bottom;

    public ImgParts() {
        this.blackstone = PictureTo2DArray.convert("src/main/resources/blackstone.bmp");
        this.intersection  = PictureTo2DArray.convert("src/main/resources/intersection.bmp");
        this.lowerLeft = PictureTo2DArray.convert("src/main/resources/lowerleft.bmp");
        this.lowerRight= PictureTo2DArray.convert("src/main/resources/lowerright.bmp");
        this.upperLeft = PictureTo2DArray.convert("src/main/resources/blackstone.bmp");
        this.upperRight  = PictureTo2DArray.convert("src/main/resources/upperright.bmp");
        this.whitestone= PictureTo2DArray.convert("src/main/resources/whitestone.bmp");
        this.debug2= PictureTo2DArray.convert("src/main/resources/debug2.bmp");
        this.debug3= PictureTo2DArray.convert("src/main/resources/debug3.bmp");
        this.left  = PictureTo2DArray.convert("src/main/resources/left.bmp");
        this.right= PictureTo2DArray.convert("src/main/resources/right.bmp");
        this.top= PictureTo2DArray.convert("src/main/resources/top.bmp");
        this.bottom= PictureTo2DArray.convert("src/main/resources/bottom.bmp");
    }
}
