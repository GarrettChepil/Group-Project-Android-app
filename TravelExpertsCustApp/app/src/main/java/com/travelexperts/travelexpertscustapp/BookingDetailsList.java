package com.travelexperts.travelexpertscustapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeEEE on 12/04/2015.
 */
public class BookingDetailsList implements Serializable {
    private List<BookingDetail> listbookings;

    public List<BookingDetail> getListbookings() {
        return listbookings;
    }

    public void setListbookings(List<BookingDetail> listbookings) {
        this.listbookings = listbookings;
    }
}

