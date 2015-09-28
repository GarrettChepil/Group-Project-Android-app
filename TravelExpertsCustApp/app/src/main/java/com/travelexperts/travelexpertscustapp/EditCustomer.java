package com.travelexperts.travelexpertscustapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class EditCustomer extends Activity {

    EditText etFirst;
    EditText etLast;
    EditText etAddress;
    EditText etCity;
    EditText etProv;
    EditText etPostal;
    EditText etCountry;
    EditText etPhone;
    EditText etBusiness;
    EditText etEmail;
    Customer customer;
    Customer newCust;
    Button btnSave;
    Button btnCancel;
    String url;
    Bookings bookingsObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        url = getString(R.string.server);

        etFirst = (EditText) findViewById(R.id.etFirst);
        etLast = (EditText) findViewById(R.id.etLast);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etCity = (EditText) findViewById(R.id.etCity);
        etProv = (EditText) findViewById(R.id.etProv);
        etPostal = (EditText) findViewById(R.id.etPostal);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etBusiness = (EditText) findViewById(R.id.etBus);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        customer = (Customer)intent.getSerializableExtra("customer");
        bookingsObj = (Bookings)intent.getSerializableExtra("bookings");


        etFirst.setText(customer.getCustFirstName());
        etLast.setText(customer.getCustLastName());
        etAddress.setText(customer.getCustAddress());
        etCity.setText(customer.getCustCity());
        etProv.setText(customer.getCustProv());
        etPostal.setText(customer.getCustPostal());
        etCountry.setText(customer.getCustCountry());
        etPhone.setText(customer.getCustHomePhone());
        etBusiness.setText(customer.getCustBusPhone());
        etEmail.setText(customer.getCustEmail());

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        newCust = new Customer();
                        newCust.setCustFirstName( etFirst.getText().toString());
                        newCust.setCustLastName(etLast.getText().toString());
                        newCust.setCustAddress(etAddress.getText().toString());
                        newCust.setCustCity(etCity.getText().toString());
                        newCust.setCustProv(etProv.getText().toString());
                        newCust.setCustPostal(etPostal.getText().toString());
                        newCust.setCustCountry(etCountry.getText().toString());
                        newCust.setCustHomePhone(etPhone.getText().toString());
                        newCust.setCustBusPhone(etBusiness.getText().toString());
                        newCust.setCustEmail(etEmail.getText().toString());

                        new updateCustomer().execute(etFirst.getText().toString(), etLast.getText().toString(),etAddress.getText().toString(), etCity.getText().toString(),
                                etProv.getText().toString(),etPostal.getText().toString(), etCountry.getText().toString(), etPhone.getText().toString(), etBusiness.getText().toString(),
                                etEmail.getText().toString(), String.valueOf(customer.getCustomerId()));
                    }
                }
        );
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), CustomerInfo.class);
                        intent.putExtra("customer", customer);
                        intent.putExtra("bookings", bookingsObj);
                        startActivity(intent);
                    }
                }
        );

    }

    public class updateCustomer extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            String s = postData(params);
            System.out.println("in async");
            return s;
        }



        protected void onPostExecute(String result) {
            //Log.d("on post ","on post execute");

            if (result.contains("true")) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CustomerInfo.class);
                intent.putExtra("customer", newCust);
                intent.putExtra("bookings", bookingsObj);
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();

                startActivity(intent);

            }
            else {
                Toast.makeText(getApplicationContext(), "There was a Problem Updating your info", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CustomerInfo.class);
                intent.putExtra("customer", customer);
                intent.putExtra("bookings", bookingsObj);
                startActivity(intent);
            }
        }




        public String postData(String valueIWantToSend[]) {

            String origresponseText = "";
            try {
                // Add your data...............
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("CustFirstName", valueIWantToSend[0]));
                nameValuePairs.add(new BasicNameValuePair("CustLastName", valueIWantToSend[1]));
                nameValuePairs.add(new BasicNameValuePair("CustAddress", valueIWantToSend[2]));
                nameValuePairs.add(new BasicNameValuePair("CustCity", valueIWantToSend[3]));
                nameValuePairs.add(new BasicNameValuePair("CustProv", valueIWantToSend[4]));
                nameValuePairs.add(new BasicNameValuePair("CustPostal", valueIWantToSend[5]));
                nameValuePairs.add(new BasicNameValuePair("CustCountry", valueIWantToSend[6]));
                nameValuePairs.add(new BasicNameValuePair("CustHomePhone", valueIWantToSend[7]));
                nameValuePairs.add(new BasicNameValuePair("CustBusPhone", valueIWantToSend[8]));
                nameValuePairs.add(new BasicNameValuePair("CustEmail", valueIWantToSend[9]));
                nameValuePairs.add(new BasicNameValuePair("CustomerId", valueIWantToSend[10]));



                HttpClient httpclient2 = new DefaultHttpClient();

                //httppost = new HttpPost("http://192.168.56.1:8080/ERPServlet/MyServlet");
                HttpPost httppost2 = new HttpPost(url + "/UpdateCustomer");

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
