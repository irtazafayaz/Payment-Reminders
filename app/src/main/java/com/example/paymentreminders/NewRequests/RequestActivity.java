package com.example.paymentreminders.NewRequests;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.paymentreminders.Interface.Common;
import com.example.paymentreminders.Model.ContactClass;
import com.example.paymentreminders.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "aa";

    RecyclerView recyclerView;
    List<ContactClass> histories = new ArrayList<>();
    Button button, next_button;
    ContactsAdapter adapter;
    boolean contactSelected = false;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        c = this;


        recyclerView = findViewById(R.id.contacts_recycler_view_id);
        adapter = new ContactsAdapter(RequestActivity.this, histories);
        button = findViewById(R.id.show_contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RequestActivity.this));
        next_button = findViewById(R.id.next_button);

        button.setOnClickListener(v -> {
            PermissionListener permissionListener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    getContactList();
                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {
                    Toast.makeText(c, "Give Permission to read contacts first", Toast.LENGTH_SHORT).show();
                }
            };

            TedPermission.with(RequestActivity.this)
                    .setPermissionListener(permissionListener)
                    .setPermissions(Manifest.permission.READ_CONTACTS)
                    .check();

        });
        next_button.setOnClickListener(v -> {
            if (Common.currentItem == null) {
                Toast.makeText(this, "Please select a contact first :D", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(RequestActivity.this, SecondRequestActivity.class);
                startActivity(intent);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                Cursor c = null;

                try {
                    c = getContentResolver().query(uri, new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE,
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                            null, null, null);
                    if (c != null && c.moveToFirst()) {
                        String number = c.getString(0);
                        int type = c.getInt(1);
                        String name = c.getString(2);
                        histories.add(new ContactClass(1, name, number, 1));
                        adapter.notifyDataSetChanged();
                        //showSelectedNumber(type, number);
//                        txtView = (TextView) findViewById(R.id.textView1);
//                        txtView.setText(number);
                    }
                } catch (Exception e) {
                    throw e;
                }

            }
        }
    }

    private void getContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    int count = 0;
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNo = phoneNo.replaceAll("\\s+", "");
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        count++;
                        histories.add(new ContactClass(count, name, phoneNo, 1));

                    }

                    pCur.close();
                }
            }
            adapter.notifyDataSetChanged();
        }
        if (cur != null) {
            cur.close();
        }
    }
}
