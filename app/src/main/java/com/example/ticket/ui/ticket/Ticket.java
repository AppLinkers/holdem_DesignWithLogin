package com.example.ticket.ui.ticket;

public class Ticket {

    String name;
    String place;
    String price;

    public Ticket (String name, String place, String price){
        this.name = name;
        this.place = place;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
