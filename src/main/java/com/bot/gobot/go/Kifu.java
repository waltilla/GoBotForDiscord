package com.bot.gobot.go;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Kifu {

    List<Stone> kifu;

    public Kifu(List<Kifu> kifu) {
        this.kifu = new ArrayList<Stone>();
    }
}
