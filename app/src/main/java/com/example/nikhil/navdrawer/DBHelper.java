package com.example.nikhil.navdrawer;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nikhil on 17/8/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CoachRegi1.db";
    public static final String EVENTS_TABLE_NAME = "Events";
    public static final String EVENTS_COLUMN_UID = "uid";
    public static final String EVENTS_COLUMN_NAME = "ename";
    public static final String EVENTS_COLUMN_DESC = "edesc";

    public static final String CALENDAR_TABLE_NAME = "Calendar";
    public static final String CALENDAR_COLUMN_UID = "uid";
    public static final String CALENDAR_COLUMN_DATE = "cdate";
    public static final String CALENDAR_COLUMN_NAME = "cname";
    public static final String CALENDAR_COLUMN_DESC = "cdesc";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EVENTS_TABLE_NAME + "(" +
                EVENTS_COLUMN_UID + " TEXT, " + EVENTS_COLUMN_NAME + " TEXT," + EVENTS_COLUMN_DESC + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + CALENDAR_TABLE_NAME + "(" +
                CALENDAR_COLUMN_UID + " TEXT, " + CALENDAR_COLUMN_DATE + " DATE,"+ CALENDAR_COLUMN_NAME + " TEXT," + CALENDAR_COLUMN_DESC + " TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Events");
        onCreate(db);
    }

    public boolean insertEvent(String uid, String ename, String edesc) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", uid);
        contentValues.put("ename", ename);
        contentValues.put("edesc", edesc);
        db.insertWithOnConflict("Events",null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        return true;
    }

    public boolean insertCalendar(String uid, String cdate, String cname, String cdesc) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", uid);
        contentValues.put("cdate", cdate);
        contentValues.put("cname", cname);
        contentValues.put("cdesc", cdesc);
        db.insertWithOnConflict("Calendar",null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENTS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<HashMap<String,String>> GetEventInfo() {

        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Events", null);

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            HashMap<String, String> map = new HashMap<String, String>();
            String uid = res.getString(res.getColumnIndex("uid"));
            String ename = res.getString(res.getColumnIndex("ename"));
            String edesc = res.getString(res.getColumnIndex("edesc"));

            map.put("uid",uid);
            map.put("ename",ename);
            map.put("edesc",edesc);

            data.add(map);
            res.moveToNext();
        }

        return data;
    }


    public ArrayList<HashMap<String,String>> GetCalendarInfo() {

        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Calendar", null);

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            HashMap<String, String> map = new HashMap<String, String>();
            String uid = res.getString(res.getColumnIndex("uid"));
            String cdate = res.getString(res.getColumnIndex("cdate"));
            String cname = res.getString(res.getColumnIndex("cname"));
            String cdesc = res.getString(res.getColumnIndex("cdesc"));

            map.put("uid",uid);
            map.put("cdate",cdate);
            map.put("cname",cname);
            map.put("cdesc",cdesc);

            data.add(map);
            res.moveToNext();
        }

        return data;
    }

    public boolean deleteNumber(String uid,String ename, String edesc){
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("DELETE FROM "+EVENTS_TABLE_NAME+" WHERE "+EVENTS_COLUMN_UID+"="+"'"+uid+"'"+" AND "+EVENTS_COLUMN_NAME+"="+"'"+ename+"'");
        db.execSQL("DELETE FROM "+EVENTS_TABLE_NAME+" WHERE "+EVENTS_COLUMN_UID+"="+"'"+uid+"'"+" AND "+EVENTS_COLUMN_NAME+"="+"'"+ename+"'");
        return true;
    }

   /* public boolean check(String name,String number){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NN+" WHERE "+COLUMN_NAME+"="+"'"+name+"'"+" AND "+COLUMN_NUMBER+"="+"'"+number+"'", null );
        if(res.getCount()>0)
            return true;
        else
            return false;
    }*/
   public Cursor getAllEvent() {
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor res = db.rawQuery( "SELECT * FROM " + EVENTS_TABLE_NAME, null );
       return res;
   }

    public Cursor getAllCalendar() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + CALENDAR_TABLE_NAME, null );
        return res;
    }

}