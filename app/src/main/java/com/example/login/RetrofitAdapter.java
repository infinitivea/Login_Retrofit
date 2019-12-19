package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.RetrofitHolder> {

    Context mContext;
    ArrayList<DeviceResponse> deviceResponseList;

    public RetrofitAdapter(Context mContext, ArrayList<DeviceResponse> deviceResponseList) {
        this.mContext = mContext;
        this.deviceResponseList = deviceResponseList;
    }

    @NonNull
    @Override
    public RetrofitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.device_item, parent, false);
        return new RetrofitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitHolder holder, int position) {
        holder.titleText.setText(position+1+ ". " +deviceResponseList.get(position).getName());
        holder.categoryText.setText(deviceResponseList.get(position).getDetail());
        if(deviceResponseList.get(position).getStatus() == 1) {
            holder.stateText.setText("อุปกรณ์ว่าง");
        }
    }

    @Override
    public int getItemCount() {
        return deviceResponseList.size();
    }

    public class RetrofitHolder extends RecyclerView.ViewHolder {

        private TextView titleText, categoryText, stateText;
        private ImageView imageView;
        private Button submitBtn;

        public RetrofitHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.ridText);
            categoryText = itemView.findViewById(R.id.uidTest);
            stateText = itemView.findViewById(R.id.eidText);
            imageView = itemView.findViewById(R.id.deviceImage);
            submitBtn = itemView.findViewById(R.id.loanBtn);
        }
    }
}
