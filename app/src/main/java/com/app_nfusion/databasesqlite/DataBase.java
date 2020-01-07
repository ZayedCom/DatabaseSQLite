package com.app_nfusion.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    SQLiteDatabase database;

    public DataBase (Context context){
        super(context, context.getDatabasePath("list.db").getAbsolutePath(), null, 3);

        database = getWritableDatabase();
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String SQL = "create table list (id integer primary key autoincrement, products text)";
        database.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProduct(String productName){

        String SQL = "insert into list (products)" + "values (?)";

        Object[] params = {productName};

        database = getWritableDatabase();
        database.execSQL(SQL, params);
        database.close();
    }

    public void clearProducts(){
        String SQL = "delete from list";

        database = getReadableDatabase();
        database.execSQL(SQL);
        database.close();
    }

    public ArrayList<ContentValues> getProduct(){

        //addProduct("Make.Beleive");
        //clearProducts();

        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from list", null);

        ArrayList<ContentValues> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", cursor.getString(0));
            contentValues.put("products", cursor.getString(1));

            arrayList.add(contentValues);
        }
        cursor.close();
        database.close();

        return arrayList;
    }
}
