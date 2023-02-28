package com.bot.gobot.go;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LogicStone {
    public String color;
    public int positionX;
    public int positionY;
    int liberties;
    List<Stone> neighbours;

    public LogicStone(String color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
