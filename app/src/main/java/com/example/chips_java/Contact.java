package com.example.chips_java;

public class Contact {

    public Contact(String name, int pinId) {
        Name = name;
        PinId = pinId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPinId() {
        return PinId;
    }

    public void setPinId(int pinId) {
        PinId = pinId;
    }

    private String Name;
    private int PinId;

}
