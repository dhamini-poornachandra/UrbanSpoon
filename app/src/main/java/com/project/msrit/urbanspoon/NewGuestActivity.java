package com.project.msrit.urbanspoon;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

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

    @BindView(R.id.add_guest)
    Button addGuestButton;

    @BindView(R.id.view)
    RelativeLayout view;

    GuestDatabaseHelper dbHelper;

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (getCurrentFocus() != null) {
                if (!phoneNumber.getText().toString().equals("") && !guestName.getText().toString().equals("")
                        && phoneNumber.getText().toString().length() == 10) {
                    addGuestButton.setEnabled(true);
                    addGuestButton.setAlpha(1.0f);
                } else {
                    addGuestButton.setEnabled(false);
                    addGuestButton.setAlpha(0.7f);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);
        ButterKnife.bind(this);
        guestName.addTextChangedListener(watcher);
        phoneNumber.addTextChangedListener(watcher);
        dbHelper = new GuestDatabaseHelper(this);
    }

    @OnClick(R.id.add_guest)
    public void addNewGuest() {
        dbHelper = new GuestDatabaseHelper(this);
        Boolean inserted = dbHelper.add_entry(guestName.getText().toString(), phoneNumber.getText().toString());

        if (inserted) {
            Snackbar.make(view, "New guest entered", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view, "Could not add new guest", Snackbar.LENGTH_LONG).show();
        }
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
//
//    private void displayAll() {
//
//        Cursor result = dbHelper.view_all();
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
//
//    private void deleteByName() {
//        int deleted = dbHelper.remove_guest_from_queue(guestName.getText().toString());
//
//        if (deleted > 0) {
//            Log.d("Deletion by Name: ", "Success ");
//        } else {
//            Log.d("Deletion by Name: ", "Failed ");
//        }
//    }
}
