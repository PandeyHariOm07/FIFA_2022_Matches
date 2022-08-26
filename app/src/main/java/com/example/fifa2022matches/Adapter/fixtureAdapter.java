package com.example.fifa2022matches.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fifa2022matches.R;
import com.example.fifa2022matches.model.FixtureData;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class fixtureAdapter extends RecyclerView.Adapter<fixtureViewHolder>{
    Context context;
    List<FixtureData> list;

    public fixtureAdapter(Context context, List<FixtureData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public fixtureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new fixtureViewHolder(LayoutInflater.from(context).inflate(R.layout.list_fixture,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull fixtureViewHolder holder, int position) {
        final FixtureData data = list.get(position);
        Picasso.get().load("https://countryflagsapi.com/png/"+data.homeName.toLowerCase()).into(holder.imageView_home);
        Picasso.get().load("https://countryflagsapi.com/png/"+data.awayName.toLowerCase()).into(holder.imageView_away);
        holder.textView_Home.setText(data.homeName);
        holder.textView_away.setText(data.awayName);
        holder.textView_match.setText(data.homeName+" vs "+ data.awayName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,d,MMM");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat givenFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = givenFormat.parse(data.date);
            holder.textView_time.setText(dateFormat.format(date)+"\n"+timeFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class fixtureViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView_home,imageView_away;
    TextView textView_Home,textView_away,textView_time,textView_match;

    public fixtureViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_home = itemView.findViewById(R.id.imageView_home);
        imageView_away = itemView.findViewById(R.id.imageView_away);
        textView_Home = itemView.findViewById(R.id.textView_Home);
        textView_away = itemView.findViewById(R.id.textView_away);
        textView_time = itemView.findViewById(R.id.textView_time);
        textView_match = itemView.findViewById(R.id.textView_match);
    }
}
