package com.noob.yusuf.bih;

/**
 * Created by yusuf on 09.07.2016.
 */
public class Room {
    public String type, fees, bedrFurn, roomFurn, dormCap, imgSrc;

    public Room(String inType,String inFees, String inBed,String inFurn,String inCap /*String imgSrc*/ ){
        type = inType;
        fees = inFees;
        bedrFurn = inBed;
        roomFurn = inFurn;
        dormCap = inCap;
    }
}
