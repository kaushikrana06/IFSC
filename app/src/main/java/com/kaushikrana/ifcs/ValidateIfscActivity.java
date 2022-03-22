package com.kaushikrana.ifcs;

import android.app.ProgressDialog;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ValidateIfscActivity extends AppCompatActivity {

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
    private TextView mMicrCode;
    private TextView mContact;
    private TextView mAddress;
    private TextView mCity;
    private TextView mDistrict;

    private String STATE;
    private String BANK;
    private String MICRCODE;
    private String BRANCH;
    private String CONTACT;
    private String ADDRESS;
    private String CITY;
    private String DISTRICT;
    private String IFSC;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_ifsc);

        db = new DatabaseHelper(this);

        mValidateIfsc = (Button) findViewById(R.id.btnValidate);
        mIfsc = (EditText) findViewById(R.id.etIFSC);
        requestQueue = Volley.newRequestQueue(this);

        mBranch = (TextView) findViewById(R.id.tvBranch);
        mBank = (TextView) findViewById(R.id.tvBank);
        mState = (TextView) findViewById(R.id.tvState);
        mMicrCode = (TextView) findViewById(R.id.tvMicr);
        mContact = (TextView) findViewById(R.id.tvContact);
        mAddress = (TextView) findViewById(R.id.tvAddress);
        mCity = (TextView) findViewById(R.id.tvCity);
        mDistrict = (TextView) findViewById(R.id.tvDistrict);

        mCvIfscDetails = (CardView) findViewById(R.id.ifscDetails);
        mCvBranch = (CardView) findViewById(R.id.ifscBranch);
        mButtons = (LinearLayout) findViewById(R.id.llButtons);

        mValidateIfsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_text = mIfsc.getText().toString().trim();

                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
                {
                    mIfsc.setError("Enter the IFSC Code");
                }
                else
                {
                    getDetails();
                    mCvIfscDetails.setVisibility(View.VISIBLE);
                    mCvBranch.setVisibility(View.VISIBLE);
                    mButtons.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    public void getDetails(){
        String ifscCode = mIfsc.getText().toString().toUpperCase();
        URL_IFSC = "http://api.techm.co.in/api/v1/ifsc/"+ifscCode;

        progressDialog = new ProgressDialog(this);
        //progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Details \n\n Please wait...");
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL_IFSC, null, new Response.Listener<JSONObject>() {

            // StringRequest stringRequest = new StringRequest(URL_IFSC, new Response.Listener<String>() {

            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try {
                    Toast.makeText(ValidateIfscActivity.this, URL_IFSC, Toast.LENGTH_LONG).show();

                    //Parsing the fetched Json String to JSON Object
                    JSONObject bankData = response.getJSONObject("data");
                    Toast.makeText(ValidateIfscActivity.this, URL_IFSC, Toast.LENGTH_LONG).show();

                    ADDRESS = bankData.getString("ADDRESS");
                    BANK = bankData.getString("BANK");
                    STATE = bankData.getString("STATE");
                    MICRCODE = bankData.getString("MICRCODE");
                    BRANCH = bankData.getString("BRANCH");
                    CONTACT = bankData.getString("CONTACT");
                    CITY = bankData.getString("CITY");
                    DISTRICT = bankData.getString("DISTRICT");
                    IFSC = bankData.getString("IFSC");

                    mBranch.setText(BRANCH);
                    mBank.setText(BANK);
                    mState.setText(STATE);
                    mMicrCode.setText(MICRCODE);
                    mContact.setText(CONTACT);
                    mAddress.setText(ADDRESS);
                    mCity.setText(CITY);
                    mDistrict.setText(DISTRICT);

                    if (BANK.isEmpty() || BANK.length() == 0 || BANK.equals("") || BANK == null) {
                        Toast.makeText(ValidateIfscActivity.this, URL_IFSC, Toast.LENGTH_LONG).show();


                    } else {
                        db.addContact(new Bank(BANK, STATE, MICRCODE, BRANCH, CONTACT, ADDRESS, CITY, DISTRICT, IFSC));
                    }

                    //Toast.makeText(ValidateIfscActivity.this, URL_IFSC, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //Adding request to the queue
        requestQueue.add(objectRequest);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
