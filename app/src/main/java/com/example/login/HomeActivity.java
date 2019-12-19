package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RetrofitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<String> call = retrofitApi.getDeviceName();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("onSuccess", response.body().toString());
                    String responseJSON = response.body().toString();
                    writeJSON(responseJSON);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeJSON(String responseJSON) {
        try {

            JSONObject object = new JSONObject(responseJSON);
            ArrayList<DeviceResponse> deviceList = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("item");

            for (int i = 0; i < jsonArray.length(); i++) {
                DeviceResponse deviceResponse = new DeviceResponse();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                deviceResponse.setId(Integer.parseInt(jsonObject.getString("id")));
                deviceResponse.setName(jsonObject.getString("device_name"));
                deviceResponse.setDetail(jsonObject.getString("device_detail"));
                deviceResponse.setStatus(Integer.parseInt(jsonObject.getString("status")));
                deviceList.add(deviceResponse);
            }

            adapter = new RetrofitAdapter(HomeActivity.this, deviceList);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
