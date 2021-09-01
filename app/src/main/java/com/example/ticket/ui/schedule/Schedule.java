package com.example.ticket.ui.schedule;


public class Schedule {

    String poster;
    String name;
    String place;
    String ticket;
    String time;

    public Schedule (String poster, String name , String place, String ticket, String time){

        this.poster = poster;
        this.name=name;
        this.place = place;
        this.ticket = ticket;
        this.time = time;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {

        this.poster = poster;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //    // 입력받은 숫자의 리스트생성
//    public static ArrayList<Schedule> createContactsList(int numContacts) {
//        ArrayList<Schedule> contacts = new ArrayList<Schedule>();
//
//        for (int i = 1; i <= numContacts; i++) {
//            contacts.add(new Schedule( R.drawable.apl, "APL", "seoul","3","14:00"));
//        }
//
//        return contacts;
//    }





}


