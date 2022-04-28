package com.kaushikrana.ifcs;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NavigatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        Button mGetIfsc = (Button) findViewById(R.id.btnGetIfsc);
        Button mValidateIfsc = (Button) findViewById(R.id.btnValidateIfsc);
        Button mHistory = (Button) findViewById(R.id.btnSearchHistory);
        Button mValidateMirc = (Button) findViewById(R.id.btnValidateMicr);



        mGetIfsc.setOnClickListener(v -> {
            Intent findIfsc = new Intent(getBaseContext(), MainActivity.class);
            startActivity(findIfsc);
        });

        mValidateIfsc.setOnClickListener(v -> {
            Intent validateIfsc = new Intent(getBaseContext(), ValidateIfscActivity.class);
            startActivity(validateIfsc);
        });
        mValidateMirc.setOnClickListener(v -> {
            Intent validateIfsc = new Intent(getBaseContext(), ValidateMircActivity.class);
            startActivity(validateIfsc);
        });

        mHistory.setOnClickListener(v -> {
            Intent history = new Intent(getBaseContext(), SearchHistoryActivity.class);
            startActivity(history);
        });
    }

}
