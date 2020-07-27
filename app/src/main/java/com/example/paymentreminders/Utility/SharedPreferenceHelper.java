package com.example.paymentreminders.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SharedPreferenceHelper {

    private static final String SHARED_PREFERENCE_TAG = "abc";
    private SharedPreferences sharedPreference;
    private Gson gson = new GsonBuilder().create();

    public SharedPreferenceHelper(Context context) {
        this.sharedPreference = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
    }

    public int getInt(String key) {
        return sharedPreference.getInt(key, 0);
    }

    public void setInt(String key, int a) {
        sharedPreference.edit().putInt(key, a).apply();
    }

    public String getString(String key) {
        return sharedPreference.getString(key, "");
    }

    public void setStrning(String key, String str) {
        sharedPreference.edit().putString(key, str).apply();

    }

    public float getfloat(String key) {
        return sharedPreference.getFloat(key, 0);
    }

    public void setFloat(String key, float a) {
        sharedPreference.edit().putFloat(key, a).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreference.getBoolean(key, true);
    }

    public void setBoolean(String key, boolean a) {
        sharedPreference.edit().putBoolean(key, a).apply();
    }

    public void saveObject(String key, Object myObject) {
        String serializedObject = gson.toJson(myObject);
        setStrning(key, serializedObject);
    }

    public Object getObject(String key) {
        String json = getString(key);
        return gson.fromJson(json, Object.class);
    }

}



