package com.bot.gobot.go;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Stone {
    public String player;
    public String color;
    public int positionX;
    public int positionY;
}
