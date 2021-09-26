package com.example.ticket.ui.home;

public class HomePub {
    String name;
    String place;
    String star;
    String price;
    String poster;

    public HomePub(String name, String place, String price,String poster){
        this.name = name;
        this.place = place;
        this.poster = poster;
        this.price = price;

    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPlace() {  return place; }

    public void setPlace(String place) { this.place = place; }

    public String getStar() {    return star; }

    public void setStar(String star) { this.star = star; }

    public String getPrice() {return price;}

    public void setPrice(String price) {this.price = price;}






}
