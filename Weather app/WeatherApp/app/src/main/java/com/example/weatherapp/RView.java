package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RView  extends RecyclerView.Adapter<RView.ViewHolder> {
    private final Context context;
    private final ArrayList<WeatherInfo> weatherList;

    public RView(Context context, ArrayList<WeatherInfo> weatherList) {
        this.context = context;
        this.weatherList=weatherList;
    }

    @NonNull
    @Override
    public RView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RView.ViewHolder holder, int position) {
            WeatherInfo item = weatherList.get(position);
        long d = Long.parseLong(item.getDate());
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("HH:mm aa")
                .format(new java.util.Date (d*1000));

        holder.showDate.setText(date);
        holder.showTemp.setText((item.getTemp()) + " \u2103");
        Glide.with(context)
                .load(item.getImg())
                .into(holder.showImg);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView showDate;
        private final TextView showTemp;
        private final ImageView showImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showDate=itemView.findViewById(R.id.showDate);
            showTemp=itemView.findViewById(R.id.showTemp);
            showImg=itemView.findViewById(R.id.showImg);
        }
    }
}
