package com.example.hanchangming.expresshelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listActivity extends AppCompatActivity {

    private String[] names = new String[]{"顺丰快递", "中通快递", "韵达快递"};
    private String[] prices = new String[64];
    double weight, height, width, length;
    String destinationProvinceSelected, destinationCitySelected;

    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();

        weight = data.getDouble("weight");
        height = data.getDouble("height");
        width = data.getDouble("width");
        length = data.getDouble("length");
        destinationProvinceSelected = data.getString("destinationProvinceSelected");
        destinationCitySelected = data.getString("destinationCitySelected");

        infoTextView = (TextView) findViewById(R.id.info);

        infoTextView.setText(destinationProvinceSelected + " " + destinationCitySelected);
        for (int i  = 0;i<names.length;i++){
            prices[i] = String.valueOf(calculatePrice());
        }


        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("names", names[i]);
            listItem.put("prices", prices[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.listitemlayout, new String[]{"names", "prices"}, new int[]{R.id.name, R.id.price});

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(simpleAdapter);
    }

    protected double calculatePrice() {
        double volume, price;
        volume = height * length * width;
        price = volume;
        return volume;
    }
}
