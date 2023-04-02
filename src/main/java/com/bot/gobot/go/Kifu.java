package com.bot.gobot.go;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Kifu {

    List<Stone> listOfStones;

    public Kifu() {
        this.listOfStones = new ArrayList<Stone>();
    }

    public void addMove(Stone stone) {
        listOfStones.add(stone);
    }

    public void setListOfStones(List<Stone> stones){
        listOfStones = stones;
    }
}
