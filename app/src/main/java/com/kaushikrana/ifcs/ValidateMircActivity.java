package com.kaushikrana.ifcs;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ValidateMircActivity extends AppCompatActivity {

    private String URL_IFSC;
    private RequestQueue requestQueue;
    private Button mValidateIfsc;
    private EditText mIfsc;
    private ProgressDialog progressDialog;
    private CardView mCvBranch;
    private CardView mCvIfscDetails;
    private LinearLayout mButtons;

    private TextView mBranch;
    private TextView mBank;
    private TextView mState;
    private TextView mAddress;
    private TextView mCity;
    private TextView mDistrict;
    private Button bt;
    private String STATE;
    private String BANK;
    private String BRANCH;
    private String ADDRESS;
    private String CITY;
    private String DISTRICT;
    private String IFSC;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_mirc);
        bt = findViewById(R.id.button2);
        db = new DatabaseHelper(this);

        mValidateIfsc = (Button) findViewById(R.id.btnValidate);
        mIfsc = (EditText) findViewById(R.id.etIFSC);
        requestQueue = Volley.newRequestQueue(this);

        mBranch = (TextView) findViewById(R.id.tvBranch);
        mBank = (TextView) findViewById(R.id.tvBank);
        mState = (TextView) findViewById(R.id.tvState);
        mAddress = (TextView) findViewById(R.id.tvAddress);
        mCity = (TextView) findViewById(R.id.tvCity);
        mDistrict = (TextView) findViewById(R.id.tvDistrict);
        mCvIfscDetails = (CardView) findViewById(R.id.ifscDetails);
        mCvBranch = (CardView) findViewById(R.id.ifscBranch);

        mValidateIfsc.setOnClickListener(v -> {
            String ed_text = mIfsc.getText().toString().trim();

            if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
            {
                mIfsc.setError("Enter a Valid MICR Code");
            }
            else
            {
                getDetails();

            }
        });
    }

    public void getDetails(){
        String ifscCode = mIfsc.getText().toString().toUpperCase();
        URL_IFSC = "https://ifsc1-database.herokuapp.com/micr/" + ifscCode;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Details \n\n Please wait...");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ValidateMircActivity.this);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, URL_IFSC, null, response -> {
            if (response.length() == 0) {
                progressDialog.dismiss();
                Toast.makeText(ValidateMircActivity.this, "Enter a valid MIRC code", Toast.LENGTH_SHORT).show();
            }
            else {
                mCvIfscDetails.setVisibility(View.VISIBLE);
                mCvBranch.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject bankData = response.getJSONObject(i);

                        ADDRESS = bankData.getString("ADDRESS");
                        BANK = bankData.getString("BANK");
                        STATE = bankData.getString("STATE");
                        BRANCH = bankData.getString("BRANCH");
                        CITY = bankData.getString("CITY");
                        DISTRICT = bankData.getString("DISTRICT");
                        IFSC = bankData.getString("IFSC");
                        progressDialog.dismiss();


                        mBranch.setText(BRANCH);
                        mBank.setText(BANK);
                        mState.setText(STATE);
                        mAddress.setText(ADDRESS);
                        mCity.setText(CITY);
                        mDistrict.setText(DISTRICT);


                        if (BANK.isEmpty() || BANK.length() == 0 || BANK.equals("") || BANK == null ) {
                            progressDialog.dismiss();

                        } else {
                            bt.setVisibility(View.VISIBLE);
                            bt.setOnClickListener(view -> {
                                Intent myIntent = new Intent(Intent.ACTION_SEND);
                                myIntent.setType("text/plain");
                                String body = "Bank Name :"+" "+BANK+"\n"+"State :"+STATE+"\n"+"District :"+DISTRICT+"\n"+"City :"+CITY+"\n"+"Branch : "+BRANCH+"\n"+"Address : "+ADDRESS+"\n"+"IFSC Code : "+IFSC;
                                String sub = "Your IFSC Code ";
                                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                                startActivity(Intent.createChooser(myIntent, "Share Using"));
                            });
                            db.addContact(new Bank(BANK, STATE, BRANCH, ADDRESS, CITY, DISTRICT, IFSC));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                error -> Toast.makeText(ValidateMircActivity.this,""+error,Toast.LENGTH_SHORT).show());
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(objectRequest);
    }

}
