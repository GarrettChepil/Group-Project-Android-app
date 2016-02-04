package com.travelexperts.travelexpertscustapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
    EditText Id, Password;
    Button submit;
    HttpPost httppost = new HttpPost();
    String s1, s2;
    String server;
    private ProgressBar pb;
    Context c;
    JSONObject responseObj;
    Customer customer;
    Bookings bookings;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        server = getString(R.string.server);

        findViewsById();

        submit.setOnClickListener(this);

    }

    private void findViewsById() {

        Id = (EditText) findViewById(R.id.Id);
        Password = (EditText) findViewById(R.id.Password);
        submit = (Button) findViewById(R.id.submit);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        c = this;
    }

    public void onClick(View view) {
        s1 = Id.getText().toString();
        s2 = Password.getText().toString();
        pb.setVisibility(View.VISIBLE);
        if (s1.matches("") || s2.matches("")) {
            Toast.makeText(c, "Credentials are Blank", Toast.LENGTH_LONG).show();
            pb.setVisibility(View.INVISIBLE);
        } else {
            new AuthService().execute(s1, s2);


        }
    }

// ASync task starting

    public class AuthService extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            String s = postData(params);

            return s;
        }



        protected void onPostExecute(String result) {
            //Log.d("on post ","on post execute");
            try {
                //System.out.println("test " + result);
                if(result.contains("connection failure...!")) {
                    Toast.makeText(getApplicationContext(), "The Authorization server is down\nPlease try again later", Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.GONE);

                }
                else if (result.contains("login")) {
                    responseObj = new JSONObject(result);
                    boolean success = responseObj.getBoolean("login");
                    if (success) {

                        //create the customer object from json
                        Gson gson = new Gson();
                        String customerInfo = responseObj.getString("CustomerInfo");
                        customer = gson.fromJson(customerInfo, Customer.class);

                        String jsonbookings = responseObj.getString("bookings");
                        System.out.println(jsonbookings);

                        //JSONArray jArray = new JSONArray(jsonbookings);
                        bookings = new Bookings();
                        TypeToken<List<Booking>> token = new TypeToken<List<Booking>>() {};
                        List<Booking> bookinglist = gson.fromJson(jsonbookings, token.getType());
                        System.out.println(bookinglist.size());
                        bookings.setListbookings(bookinglist);

                        Toast.makeText(getApplicationContext(), "Logged in Susccessful", Toast.LENGTH_LONG).show();


                        Intent intent_name = new Intent();
                        intent_name.setClass(getApplicationContext(), CustomerInfo.class);
                        intent_name.putExtra("customer", customer);
                        intent_name.putExtra("bookings", bookings);
                        pb.setVisibility(View.GONE);

                        startActivity(intent_name);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login Unsuccesful\nPlease check your credentials and try again", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Problem connecting to the server\nPlease check your internet settings", Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        protected void onProgressUpdate(Integer... progress) {
            pb.setProgress(progress[0]);
        }


        public String postData(String valueIWantToSend[]) {

            String origresponseText = "";
            try {
                // Add your data...............
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("username", valueIWantToSend[0]));

                nameValuePairs.add(new BasicNameValuePair("password", valueIWantToSend[1]));

                HttpClient httpclient = new DefaultHttpClient();

                //httppost = new HttpPost("http://192.168.56.1:8080/ERPServlet/MyServlet");
                httppost = new HttpPost(server + "/AuthService");

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);

                origresponseText = readContent(response);

                httpclient.getConnectionManager().shutdown();
                System.out.println("Connection close");

            } catch (IOException e) {
                // TODO Auto-generated catch block
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


            return text;


        }
    }
}