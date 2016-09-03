package com.example.nikhil.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class EventsFragment extends Fragment {

    DBHelper dbHelper;
    ListView eventLV;
    ArrayList<HashMap<String,String>> Data = new ArrayList<HashMap<String, String>>();


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext());
        Data = dbHelper.GetEventInfo();
        View eventview = inflater.inflate(R.layout.fragment_events, container, false);
        eventLV = (ListView)eventview.findViewById(R.id.eventlistviewitem);
        eventLV.setAdapter(new EventAdapter(getContext(),Data));


        // Inflate the layout for this fragment
        return eventview;
    }

}
