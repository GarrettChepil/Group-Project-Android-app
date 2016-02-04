package com.travelexperts.travelexpertscustapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomDetailsAdapter extends ArrayAdapter<BookingDetail>{


    public CustomDetailsAdapter(Context context, List<BookingDetail> resource) {
        super (context, R.layout.custom_bookingdetails_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        if(convertView == null) {
            convertView = myInflater.inflate(R.layout.custom_bookingdetails_row,parent, false);
        }
        BookingDetail booking = getItem(position);

        TextView tvItineraryNo = (TextView) convertView.findViewById(R.id.tvItineraryNo);
        TextView tvDestination = (TextView) convertView.findViewById(R.id.tvDestination);
        TextView tvTripStart = (TextView) convertView.findViewById(R.id.tvTripStart);
        TextView tvTripEnd = (TextView) convertView.findViewById(R.id.tvTripEnd);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        TextView tvClassId = (TextView) convertView.findViewById(R.id.tvClassId);
        TextView tvRegion = (TextView) convertView.findViewById(R.id.tvRegion);


        tvItineraryNo.setText("Itinerary: " + booking.getItineraryNo());
        tvDestination.setText(booking.getDestination());
        tvTripStart.setText("Start: " + booking.getTripStart());
        tvTripEnd.setText("End: " + booking.getTripEnd());
        tvDescription.setText(booking.getDescription());
        tvClassId.setText("ClassId: " + booking.getClassId());
        tvRegion.setText("Region: " + booking.getRegionId());


        return convertView;
    }
}
