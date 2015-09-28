package com.travelexperts.travelexpertscustapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


public class BookingDetailsActivity extends ActionBarActivity {

    BookingDetailsList bookingDetailsObj;
    ListAdapter bookingAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);


        Intent intent = getIntent();
        bookingDetailsObj = (BookingDetailsList)intent.getSerializableExtra("bookingdetails");

        ListView bookingList = (ListView) findViewById(R.id.lstBookingDetails);
        bookingAdapter = new CustomDetailsAdapter(this, bookingDetailsObj.getListbookings());
        bookingList.setAdapter(bookingAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booking_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
