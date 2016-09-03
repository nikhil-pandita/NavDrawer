package com.example.nikhil.navdrawer;

/**
 * Created by nikhil on 1/9/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarAdapter extends BaseAdapter{
    Context context;

    ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String, String>>();
    private static LayoutInflater inflater=null;
    public CalendarAdapter (Context mainActivity, ArrayList<HashMap<String,String>> imagesdata) {
        // TODO Auto-generated constructor stub
        result=imagesdata;
        context=mainActivity;

        //imageLoader.destroy();
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView name;
        TextView desc;
        TextView date;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.calendarlistitem, null);
        holder.date = (TextView)rowView.findViewById(R.id.calendardateitem);
        holder.name = (TextView)rowView.findViewById(R.id.calendarnameitem);
        holder.desc = (TextView)rowView.findViewById(R.id.calendardescitem);
        holder.date.setText(result.get(position).get("cdate"));
        holder.name.setText(result.get(position).get("cname"));
        holder.desc.setText(result.get(position).get("cdesc"));


        return rowView;
    }

}
