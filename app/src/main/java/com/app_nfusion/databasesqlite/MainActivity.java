package com.app_nfusion.databasesqlite;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase;
    ArrayList<ContentValues> arrayList = new ArrayList<>();

    //Simple Array
    //String listArray[] = {"Xperia", "Galaxy", "iPhone", "Pixel"};

    ListView listView;
    TextView textView;
    EditText editText;
    Button addButton;
    Button clearButton;

    String text = "PRODUCT DEMO";

    BaseAdapter baseAdapter;
    //ArrayAdapter<String> arrayAdapter;

    public void addButton (View view){
        text = String.valueOf(editText.getText());
        dataBase.addProduct(text);
        onResume();
    }

    public void clearButton(View view){
        dataBase.clearProducts();
        onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);

        //We have to use BaseAdapter instead of Array Adapter cause it DataBase won't work with ArrayAdapter
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.item);
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);

        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }
                TextView textView = convertView.findViewById(R.id.item);

                ContentValues contentValues = arrayList.get(position);
                textView.setText(contentValues.getAsString("products"));

                return convertView;
            }
        };

        listView.setAdapter(baseAdapter);
        //listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList = dataBase.getProduct();
        baseAdapter.notifyDataSetChanged();
    }
}
