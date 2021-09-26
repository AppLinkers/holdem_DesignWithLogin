package com.example.ticket.ui.home;

public class HomeSchdeule {
    String name;
    String place;
    String date;
    String cmpPoster;

    public HomeSchdeule(String name, String place, String date,String cmpPoster){
        this.name = name;
        this.place = place;
        this.date = date;
        this.cmpPoster = cmpPoster;

    }

    public String getCmpPoster() {
        return cmpPoster;
    }

    public void setCmpPoster(String cmpPoster) {
        this.cmpPoster = cmpPoster;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
