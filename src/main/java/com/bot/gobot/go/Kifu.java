package com.bot.gobot.go;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Kifu {

    List<Stone> kifu;

    public Kifu() {
        this.kifu = new ArrayList<Stone>();
    }

    public void addMove(Stone stone) {
        kifu.add(stone);
    }
}
