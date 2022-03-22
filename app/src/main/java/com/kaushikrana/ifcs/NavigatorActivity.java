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
        Button mSearchByMicr = (Button) findViewById(R.id.btnMicr);
        Button mHistory = (Button) findViewById(R.id.btnSearchHistory);

        mGetIfsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIfsc = new Intent(getBaseContext(), MainActivity.class);
                startActivity(findIfsc);
            }
        });

        mValidateIfsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validateIfsc = new Intent(getBaseContext(), ValidateIfscActivity.class);
                startActivity(validateIfsc);
            }
        });

        mSearchByMicr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validateMicr = new Intent(getBaseContext(), SearchMicrActivity.class);
                startActivity(validateMicr);
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(getBaseContext(), SearchHistoryActivity.class);
                startActivity(history);
            }
        });
    }

}
