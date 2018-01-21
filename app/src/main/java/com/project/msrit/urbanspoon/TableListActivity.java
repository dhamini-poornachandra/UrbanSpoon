package com.project.msrit.urbanspoon;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dhamini-poorna-chandra on 08/01/18.
 */


public class TableListActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    TableDatabaseHelper dbHelper;
    ArrayList<String> tables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);
        dbHelper = new TableDatabaseHelper(getApplicationContext());
        displayAll();
        // data to populate the RecyclerView with
//
//        tables.add("Table 1");
//        tables.add("Table 2");
//        tables.add("Table 3");
//        tables.add("Table 4");
//        tables.add("Table 5");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tables);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void displayAll() {

        Cursor result = dbHelper.view_all();
        if (result.getCount() == 0) {
            Log.d("Error", "Nothing to show");
        } else {
//            StringBuffer stringBuffer = new StringBuffer();

            while (result.moveToNext()) {
                tables.add(result.getString(1) + "\n");
//                stringBuffer.append("Name: " + result.getString(1) + "\n");
//                stringBuffer.append("Availability: " + result.getString(2) + "\n");
            }
//            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}