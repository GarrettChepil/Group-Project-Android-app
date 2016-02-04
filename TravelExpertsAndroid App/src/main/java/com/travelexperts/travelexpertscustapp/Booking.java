package com.travelexperts.travelexpertscustapp;

import java.io.Serializable;

/**
 * Created by GeEEE on 11/04/2015.
 */

public class Booking implements Serializable {
    int BookingId;
    String BookingDate;
    String BookingNo;
    int TravelerCount;
    String TripTypeId;


    public int getBookingId() {
        return BookingId;
    }
    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }
    public String getBookingDate() {
        return BookingDate;
    }
    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }
    public String getBookingNo() {
        return BookingNo;
    }
    public void setBookingNo(String bookingNo) {
        BookingNo = bookingNo;
    }
    public int getTravelerCount() {
        return TravelerCount;
    }
    public void setTravelerCount(int travelerCount) {
        TravelerCount = travelerCount;
    }
    public String getTripTypeId() {
        return TripTypeId;
    }
    public void setTripTypeId(String tripTypeId) {
        TripTypeId = tripTypeId;
    }



}

