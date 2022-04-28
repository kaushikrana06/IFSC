package com.kaushikrana.ifcs;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SearchHistoryActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private String [] list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        db = new DatabaseHelper(this);
        final List<Bank> banks = db.getAllContacts();
        list = new String[banks.size()];

        for (int i = 0; i < list.length; i++) {
            list[i] = "Bank : "+(String) banks.get(i).get_bank()+"\n"+ "IFSC : "+ (String) banks.get(i).get_ifsc()
                    +"\n"+ "Address : "+ (String) banks.get(i).get_address()+"\n" +"\n"+ "Branch : "+ (String) banks.get(i).get_branch()+"\n"+ "City : "+ (String) banks.get(i).get_city();
        }
        if (list != null && list.length > 0 ) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_bank_row, list);
            listView = (ListView) findViewById(R.id.lvContactsSql);
            listView.setAdapter(adapter);
        }

    }
}

