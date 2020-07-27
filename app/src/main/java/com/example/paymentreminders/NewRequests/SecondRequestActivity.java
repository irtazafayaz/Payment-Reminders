package com.example.paymentreminders.NewRequests;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentreminders.HomePageActivity;
import com.example.paymentreminders.Interface.Common;
import com.example.paymentreminders.Interface.LoggedUser;
import com.example.paymentreminders.Model.Requests;
import com.example.paymentreminders.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SecondRequestActivity extends Activity {
    int _hour, _minute, _date, _month, _year;
    TextView name, date, time;
    String s_details, s_title, s_price, s_date, s_time;
    TextInputEditText details, title, price;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    boolean timeSet = false, dateSet = false;
    Button setReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_request);
        name = findViewById(R.id.name);
        date = findViewById(R.id.select_date);
        time = findViewById(R.id.select_time);
        details = findViewById(R.id.details_text_input_field);
        title = findViewById(R.id.title_text_input_field);
        price = findViewById(R.id.payment_text_input_field);

        date.setOnClickListener(v -> setDate());
        time.setOnClickListener(v -> setTime());

        name.setText(Common.currentItem.getName());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("requests");

        setReminder = findViewById(R.id.setReminder);
        setReminder.setOnClickListener(v -> {

            if (timeSet && dateSet && details.getText() != null && title.getText() != null && price.getText() != null) {

                String key = databaseReference.push().getKey();

                s_details = details.getText().toString();
                s_title = title.getText().toString();
                s_price = price.getText().toString();
                Requests requests = new Requests(key,
                        Common.currentItem.getName(),
                        Common.currentItem.getPhoneNo(), LoggedUser.currentItem.getUsername(), LoggedUser.currentItem.getPhoneNo(),
                        s_price, s_date, s_time, s_details, s_title, "Requested");
                if (key != null) {
                    databaseReference.child(key).setValue(requests);
                    Toast.makeText(this, "Data entered in database", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondRequestActivity.this, HomePageActivity.class);
                    startActivity(intent);

                }
            }
        });


    }

    public void setDate() {

        Calendar calendar = Calendar.getInstance();
        _year = calendar.get(Calendar.YEAR);
        _date = calendar.get(Calendar.DAY_OF_MONTH);
        _month = calendar.get(Calendar.MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            _year = year;
            _month = month + 1;
            _date = dayOfMonth;
            s_date = _date + "/" + _month + "/" + _year;
            date.setText(s_date);

        }, _year, _month, _date);
        datePickerDialog.show();
        Toast.makeText(this, "Date = " + _date + _month + _year, Toast.LENGTH_SHORT).show();
        dateSet = true;
    }


    public void setTime() {
        Calendar calendar = Calendar.getInstance();
        _hour = calendar.get(Calendar.HOUR);
        _minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            _hour = hourOfDay;
            _minute = minute;
            s_time = _hour + ":" + _minute;
            time.setText(s_time);

        }, _hour, _minute, true);
        timePickerDialog.show();
        timeSet = true;

    }


}
