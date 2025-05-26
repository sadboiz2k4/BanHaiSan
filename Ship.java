package com.example.project.Model;

public class Ship {
    private int shipID;
    private String shipName;
    private int shipPrice;
    private String shipDescription;
    private String carrier;
    public Ship() {
    }

    public Ship(int shipID, String shipName, int shipPrice, String shipDescription, String carrier) {
        this.shipID = shipID;
        this.shipName = shipName;
        this.shipPrice = shipPrice;
        this.shipDescription = shipDescription;
        this.carrier = carrier;
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(int shipPrice) {
        this.shipPrice = shipPrice;
    }

    public String getShipDescription() {
        return shipDescription;
    }

    public void setShipDescription(String shipDescription) {
        this.shipDescription = shipDescription;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
