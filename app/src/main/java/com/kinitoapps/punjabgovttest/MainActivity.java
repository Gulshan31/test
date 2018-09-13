package com.kinitoapps.punjabgovttest;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String URL_SPINNER = "https://governmentappcom.000webhostapp.com/spinner.php";
    String URL_SPINNER2 = "https://governmentappcom.000webhostapp.com/spinneredu.php";
    String text;
    TextView tv;
    Spinner spinnerLocation,spinnerEducation;
    HashMap<String,String> hashMap;
    ArrayAdapter<String> arrayAdapter,arrayAdapter2;

    ArrayList<String> locations,education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        locations = new ArrayList<>();
        education = new ArrayList<>();
        final TextInputEditText fullName = findViewById(R.id.fullnameedittext);
        final TextInputEditText phNo = findViewById(R.id.phonenumberedittext);
        hashMap = new HashMap<>();
        spinnerLocation = findViewById(R.id.location);
        spinnerEducation = findViewById(R.id.education);
        arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, education);
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, locations);
        spinnerLocation.setAdapter(arrayAdapter);
        spinnerEducation.setAdapter(arrayAdapter2);
        loadSpinnerData();
        loadSpinner2Data();
        Button btn = findViewById(R.id.nextbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpSecond.class);
                hashMap.put("name",fullName.getText().toString());
                hashMap.put("phone",phNo.getText().toString());
                hashMap.put("location", spinnerLocation.getSelectedItem().toString());
                hashMap.put("education",spinnerEducation.getSelectedItem().toString());
                intent.putExtra("hashmap",hashMap);
                startActivity(intent);

            }
        });


    }

    private void loadSpinner2Data() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SPINNER2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for(int i=0;i<array.length();i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                education.add(jsonObject1.getString("EDUCATION"));
                            }


                            //traversing through all the object
//                            for (int i = 0; i < array.length(); i++) {
//
//                                //getting product object from json array
//                                JSONObject product = array.getJSONObject(i);
//
//                                //adding the product to product list
//
//
//                                productList.add(new Product(
//                                        product.getInt("id"),
//                                        product.getString("title"),
//                                        product.getString("shortdesc"),
//                                        product.getDouble("rating"),
//                                        product.getDouble("price"),
//                                        product.getString("image")
//                                ));
//                            }

                            //creating adapter object and setting it to recyclerview
                            arrayAdapter2.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void loadSpinnerData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SPINNER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for(int i=0;i<array.length();i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                locations.add(jsonObject1.getString("LOCATION"));
                            }


                            //traversing through all the object
//                            for (int i = 0; i < array.length(); i++) {
//
//                                //getting product object from json array
//                                JSONObject product = array.getJSONObject(i);
//
//                                //adding the product to product list
//
//
//                                productList.add(new Product(
//                                        product.getInt("id"),
//                                        product.getString("title"),
//                                        product.getString("shortdesc"),
//                                        product.getDouble("rating"),
//                                        product.getDouble("price"),
//                                        product.getString("image")
//                                ));
//                            }

                            //creating adapter object and setting it to recyclerview
                            arrayAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

    }


}
