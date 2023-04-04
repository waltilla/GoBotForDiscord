package com.bot.gobot.img;

import lombok.Getter;
import lombok.Setter;

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
    int[][] san;

    public ImgParts() {
        this.blackstone = PictureTo2DArray.convert("blackstone.bmp");
        this.intersection  = PictureTo2DArray.convert("intersection.bmp");
        this.lowerLeft = PictureTo2DArray.convert("lowerleft.bmp");
        this.lowerRight= PictureTo2DArray.convert("lowerright.bmp");
        this.upperLeft = PictureTo2DArray.convert("upperleft.bmp");
        this.upperRight  = PictureTo2DArray.convert("upperright.bmp");
        this.whitestone= PictureTo2DArray.convert("whitestone.bmp");
        this.debug2= PictureTo2DArray.convert("debug2.bmp");
        this.debug3= PictureTo2DArray.convert("debug3.bmp");
        this.left  = PictureTo2DArray.convert("left.bmp");
        this.right= PictureTo2DArray.convert("right.bmp");
        this.top= PictureTo2DArray.convert("top.bmp");
        this.bottom= PictureTo2DArray.convert("bottom.bmp");
        this.san= PictureTo2DArray.convert("san.bmp");
    }
}
