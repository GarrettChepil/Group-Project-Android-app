package com.travelexperts.travelexpertscustapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class CustomBookingAdapter extends ArrayAdapter<Booking> {

    public CustomBookingAdapter(Context context, List<Booking> resource) {
        super (context, R.layout.custom_booking_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        if(convertView == null) {
            convertView = myInflater.inflate(R.layout.custom_booking_row,parent, false);
        }
        Booking booking = getItem(position);

        TextView tvBookingNo = (TextView) convertView.findViewById(R.id.tvBookingNo);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvTravelCount = (TextView) convertView.findViewById(R.id.tvTravelCount);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);

        tvBookingNo.setText("Booking No: " + booking.getBookingNo());
        tvDate.setText("Date: " + booking.getBookingDate());
        tvTravelCount.setText("Travel Count: " + booking.getTravelerCount());
        tvType.setText("Trip Type: " + booking.getTripTypeId());

        return convertView;
    }
}
