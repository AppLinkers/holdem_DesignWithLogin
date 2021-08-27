package com.example.ticket.ui.pub;

public class Pub {

    String name;
    String place;
    String game;
    String time;

    public Pub (String name, String place, String game, String time) {

        this.name =name;
        this.place =place;
        this.game = game;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
