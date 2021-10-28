package com.example.ticket.ui.pub;


public class PubMenu {

    String Menu_name;
    String Menu_price;

    public PubMenu(String Menu_name,String Menu_price){
        this.Menu_name = Menu_name;
        this.Menu_price = Menu_price;
    }

    public String getMenu_name() {
        return Menu_name;
    }

    public void setMenu_name(String menu_name) {
        Menu_name = menu_name;
    }

    public String getMenu_price() {
        return Menu_price;
    }

    public void setMenu_price(String menu_price) {
        Menu_price = menu_price;
    }
}
