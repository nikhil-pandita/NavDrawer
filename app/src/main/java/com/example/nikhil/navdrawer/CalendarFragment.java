package com.example.nikhil.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    DBHelper dbHelper;
    ListView eventLV;
    ArrayList<HashMap<String,String>> Data = new ArrayList<HashMap<String, String>>();


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbHelper = new DBHelper(getContext());
        Data = dbHelper.GetCalendarInfo();
        View eventview = inflater.inflate(R.layout.fragment_calendar, container, false);
        eventLV = (ListView)eventview.findViewById(R.id.calendarlistitem);
        eventLV.setAdapter(new CalendarAdapter(getContext(),Data));


        // Inflate the layout for this fragment
        return eventview;

    }

}
