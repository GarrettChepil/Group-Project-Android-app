package com.travelexperts.travelexpertscustapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CustomerInfo extends ActionBarActivity {

    String url;

    HttpPost httppost2 = new HttpPost();
    Context c;
    TextView name;
    TextView address;
    TextView phone;
    TextView bus;
    TextView email;
    BookingDetailsList bookingDetails;
    Customer customer;
    Bookings bookingsObj;
    ListAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);
        url = getString(R.string.server);


        name = (TextView) findViewById(R.id.txtName);
        address = (TextView) findViewById(R.id.txtAddress);
        phone = (TextView) findViewById(R.id.txtPhone);
        bus = (TextView) findViewById(R.id.txtBus);
        email = (TextView) findViewById(R.id.txtEmail);
        ListView bookingList = (ListView) findViewById(R.id.lstBookings);

        Intent intent = getIntent();
        customer = (Customer)intent.getSerializableExtra("customer");
        bookingsObj = (Bookings)intent.getSerializableExtra("bookings");

        name.setText(customer.getCustFirstName() + " " + customer.getCustLastName());
        address.setText(customer.getCustAddress() + "\n" + customer.getCustCity() + ", " + customer.getCustProv() + ", " + customer.getCustPostal());
        phone.setText("Home phone: " + customer.getCustHomePhone());
        bus.setText("Business phone: " + customer.getCustBusPhone());
        email.setText("Email: " + customer.getCustEmail());

        bookingAdapter = new CustomBookingAdapter(this, bookingsObj.getListbookings());
        bookingList.setAdapter(bookingAdapter);

        bookingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Booking booking = (Booking)bookingAdapter.getItem(position);
                        String bookingId = String.valueOf(booking.BookingId);
                        new GetBookingDetails().execute(bookingId);


                    }
                }
        );






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_info, menu);
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
        if(id == R.id.editCust) {
            Intent editIntent = new Intent();
            editIntent.setClass(getApplicationContext(), EditCustomer.class);
            editIntent.putExtra("customer", customer);
            editIntent.putExtra("bookings", bookingsObj);
            startActivity(editIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }







    public class GetBookingDetails extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            String s = postData(params);
            System.out.println("in async");
            return s;
        }



        protected void onPostExecute(String result) {
            //Log.d("on post ","on post execute");

            if (result.contains("bookings")) {
                try {
                    Gson gson = new Gson();
                    JSONObject responseObj = new JSONObject(result);
                    String jsonbookings = responseObj.getString("bookings");

                    bookingDetails = new BookingDetailsList();
                    TypeToken<List<BookingDetail>> token = new TypeToken<List<BookingDetail>>() {
                    };
                    List<BookingDetail> bookinglist = gson.fromJson(jsonbookings, token.getType());
                    bookingDetails.setListbookings(bookinglist);


                    Intent intent_name = new Intent();
                    intent_name.setClass(getApplicationContext(), BookingDetailsActivity.class);
                    intent_name.putExtra("bookingdetails", bookingDetails);

                    startActivity(intent_name);
                }
             catch (JSONException e) {
                e.printStackTrace();
            }

            }
            else {
                Toast.makeText(getApplicationContext(), "Your Internet Connection is Poor.Please try again later", Toast.LENGTH_LONG).show();
            }
        }




        public String postData(String valueIWantToSend[]) {

            String origresponseText = "";
            try {
                // Add your data...............
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("bookingId", valueIWantToSend[0]));


                HttpClient httpclient2 = new DefaultHttpClient();

                //httppost = new HttpPost("http://192.168.56.1:8080/ERPServlet/MyServlet");
                httppost2 = new HttpPost(url + "/GetBookingDetails");

                httppost2.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient2.execute(httppost2);

                origresponseText = readContent(response);

                httpclient2.getConnectionManager().shutdown();
                System.out.println("Connection close");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            return origresponseText;


        }


        String readContent(HttpResponse response) {

            String text = "";
            InputStream in = null;


            try {
                in = response.getEntity().getContent();


                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                    //InputStreamReader(instream, "UTF-8"), 8000);


                }
                in.close();
                text = sb.toString();

            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception ex) {
                }
            }

            System.out.println(text);

            return text;


        }
    }

}
