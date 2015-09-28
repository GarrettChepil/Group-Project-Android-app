package com.travelexperts.travelexpertscustapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeEEE on 11/04/2015.
 */
public class Bookings implements Serializable {

    private List<Booking> listbookings;

    public List<Booking> getListbookings() {
        return listbookings;
    }

    public void setListbookings(List<Booking> listbookings) {
        this.listbookings = listbookings;
    }
}
