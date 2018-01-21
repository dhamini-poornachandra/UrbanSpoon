package com.project.msrit.urbanspoon;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTableActivity extends AppCompatActivity {

    @BindView(R.id.table_name_edit_ext)
    EditText tableName;

    TableDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);
        ButterKnife.bind(this);
        dbHelper = new TableDatabaseHelper(getApplicationContext());
    }

    @OnClick(R.id.add_table)
    public void addNewGuest() {
        //TODO: add new guest
        Boolean inserted = dbHelper.add_entry(tableName.getText().toString().toUpperCase(), "true");

        if (inserted) {
            Log.d("Inserted value", "success");
//            displayAll();
//            deleteByName();
            update();
        } else {
            Log.d("Could not insert value", "failed");
        }

//        update();
    }

//    private void retrieve() {
//        Cursor result = dbHelper.retrieve_next_guest();
//
//        if (result.getCount() == 0) {
//            Log.d("Error", "Nothing to show");
//        } else {
//            StringBuffer stringBuffer = new StringBuffer();
//
//            while (result.moveToNext()) {
//                stringBuffer.append("Name: " + result.getString(0) + "\n");
//                stringBuffer.append("PhoneNumber: " + result.getString(1) + "\n");
//            }
//            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }

    private void displayAll() {

        Cursor result = dbHelper.view_all();
        if (result.getCount() == 0) {
            Log.d("Error", "Nothing to show");
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            while (result.moveToNext()) {
                stringBuffer.append("Name: " + result.getString(1) + "\n");
                stringBuffer.append("Availability: " + result.getString(2) + "\n");
            }
            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteByName() {
        int deleted = dbHelper.remove_table(tableName.getText().toString());

        if (deleted > 0) {
            Log.d("Deletion by Name: ", "Success ");
            displayAll();
        } else {
            Log.d("Deletion by Name: ", "Failed ");
        }
    }

    public void update() {
        if (dbHelper.update(tableName.getText().toString(), "false")) {
            displayAll();
        } else {
            Log.d("Error", "error");
        }
    }
}
