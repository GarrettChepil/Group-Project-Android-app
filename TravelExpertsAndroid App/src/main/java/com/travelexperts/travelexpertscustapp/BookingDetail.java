package com.travelexperts.travelexpertscustapp;

import java.io.Serializable;

/**
 * Created by GeEEE on 12/04/2015.
 */
public class BookingDetail implements Serializable {
    int BookingDetailId;
    String ItineraryNo;
    String TripStart;
    String TripEnd;
    String Description;
    String Destination;
    String BasePrice;
    String RegionId;
    String ClassId;
    public int getBookingDetailId() {
        return BookingDetailId;
    }
    public void setBookingDetailId(int bookingDetailId) {
        BookingDetailId = bookingDetailId;
    }
    public String getItineraryNo() {
        return ItineraryNo;
    }
    public void setItineraryNo(String itineraryNo) {
        ItineraryNo = itineraryNo;
    }
    public String getTripStart() {
        return TripStart;
    }
    public void setTripStart(String tripStart) {
        TripStart = tripStart;
    }
    public String getTripEnd() {
        return TripEnd;
    }
    public void setTripEnd(String tripEnd) {
        TripEnd = tripEnd;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getDestination() {
        return Destination;
    }
    public void setDestination(String destination) {
        Destination = destination;
    }
    public String getBasePrice() {
        return BasePrice;
    }
    public void setBasePrice(String basePrice) {
        BasePrice = basePrice;
    }
    public String getRegionId() {
        return RegionId;
    }
    public void setRegionId(String regionId) {
        RegionId = regionId;
    }
    public String getClassId() {
        return ClassId;
    }
    public void setClassId(String classId) {
        ClassId = classId;
    }




}
