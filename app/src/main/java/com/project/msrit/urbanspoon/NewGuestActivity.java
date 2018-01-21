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

/**
 * Created by dhamini-poorna-chandra on 08/01/18.
 */

public class NewGuestActivity extends AppCompatActivity {

    @BindView(R.id.guest_name_edit_ext)
    EditText guestName;

    @BindView(R.id.phone_number_edit_text)
    EditText phoneNumber;
    GuestDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);
        ButterKnife.bind(this);
        dbHelper = new GuestDatabaseHelper(getApplicationContext());
    }

    @OnClick(R.id.add_guest)
    public void addNewGuest() {
        //TODO: add new guest
        Boolean inserted = dbHelper.add_entry(guestName.getText().toString(), phoneNumber.getText().toString());

        if (inserted) {
            Log.d("Inserted value", "success");
        } else {
            Log.d("Could not insert value", "failed");
        }
    }

    private void retrieve() {
        Cursor result = dbHelper.retrieve_next_guest();

        if (result.getCount() == 0) {
            Log.d("Error", "Nothing to show");
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            while (result.moveToNext()) {
                stringBuffer.append("Name: " + result.getString(0) + "\n");
                stringBuffer.append("PhoneNumber: " + result.getString(1) + "\n");
            }
            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayAll() {

        Cursor result = dbHelper.view_all();
        if (result.getCount() == 0) {
            Log.d("Error", "Nothing to show");
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            while (result.moveToNext()) {
                stringBuffer.append("Name: " + result.getString(0) + "\n");
                stringBuffer.append("PhoneNumber: " + result.getString(1) + "\n");
            }
            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteByName() {
        int deleted = dbHelper.remove_guest_from_queue(guestName.getText().toString());

        if (deleted > 0) {
            Log.d("Deletion by Name: ", "Success ");
        } else {
            Log.d("Deletion by Name: ", "Failed ");
        }
    }
}
