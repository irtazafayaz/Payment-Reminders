package com.example.paymentreminders.Utility;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/abc")
    Call<ResponseBody> getallData();


}
