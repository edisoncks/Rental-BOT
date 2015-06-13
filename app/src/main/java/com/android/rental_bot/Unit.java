package com.android.rental_bot;

/**
 * Created by edison on 6/13/15.
 */
public class Unit {
    public String unitArea;
    public String unitAddress;
    public String furnitureOption;
    public String tenantGender;
    public int roomAmount;
    public int slot;
    public int unitRental;
    public int imageResource = R.drawable.default_image;

    public Unit(int imageResource, String unitAddress, String unitArea, int unitRental, String furnitureOption, int roomAmount, int slot, String tenantGender) {
        this.imageResource = imageResource;
        this.unitAddress = unitAddress;
        this.unitArea = unitArea;
        this.unitRental = unitRental;
        this.furnitureOption = furnitureOption;
        this.roomAmount = roomAmount;
        this.slot = slot;
        this.tenantGender = tenantGender;
    }

    public String getUnitRental() {
        String rental = Integer.toString(unitRental);
        return ("RM ".concat(rental).concat(" / Month"));
    }
}