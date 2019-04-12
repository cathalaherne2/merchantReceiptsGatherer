package com.example.outla.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {


    JsonCreator jsonCreator = new JsonCreator();
    String shopName;
    String location;
    String item1;
    String item2;
    String item3;
    String item4;
    String item5;
    Button ButtonGenerate;
    JSONObject json;
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button generateInfo = findViewById(R.id.generate);
        final Spinner shopName = findViewById(R.id.shopName);
        final Spinner location = findViewById(R.id.location);
        final Spinner item1 = findViewById(R.id.item1);
        final Spinner item2 = findViewById(R.id.item2);
        final Spinner item3 = findViewById(R.id.item3);
        final Spinner item4 = findViewById(R.id.item4);
        final Spinner item5 = findViewById(R.id.item5);


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
            Toast.makeText(MainActivity.this,
                    "nfcAdapter==null, no NFC adapter exists",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,
                    "Set Callback(s)",
                    Toast.LENGTH_LONG).show();
            nfcAdapter.setNdefPushMessageCallback(this, this);
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }

        List<String> ShopNames = new ArrayList<>();
        ShopNames.add("Tesco");
        ShopNames.add("Dunnes");
        ShopNames.add("Aldi");
        ShopNames.add("Lidl");
        ShopNames.add("Mc donalds");

        List<String> locations = new ArrayList<>();
        locations.add("Parnell Street");
        locations.add("Newbridge");
        locations.add("Central Park");
        locations.add("Stephens Green");
        locations.add("Grafton Street");

        List<String> item1s = new ArrayList<>();
        item1s.add("Eggs");
        item1s.add("Burger");
        item1s.add("Soap");
        item1s.add("Bread");
        item1s.add("Flowers");

        List<String> item2s = new ArrayList<>();
        item2s.add("Mince");
        item2s.add("Pancakes");
        item2s.add("Chicken Breast");
        item2s.add("Wine");
        item2s.add("Beer");

        List<String> item3s = new ArrayList<>();
        item3s.add("Muffins");
        item3s.add("Chocolate");
        item3s.add("7up");
        item3s.add("Bananas");
        item3s.add("Apple");

        List<String> item4s = new ArrayList<>();
        item4s.add("Eclair");
        item4s.add("Birthday Cake");
        item4s.add("Mi wadi");
        item4s.add("Biscuits");
        item4s.add("Pringles");

        List<String> item5s = new ArrayList<>();
        item5s.add("Wipes");
        item5s.add("Bleach");
        item5s.add("Washing Liquid");
        item5s.add("Flour");
        item5s.add("Jelly");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ShopNames);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item1s);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item2s);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item3s);
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item4s);
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, item5s);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        shopName.setAdapter(dataAdapter);
        location.setAdapter(dataAdapter1);
        item1.setAdapter(dataAdapter2);
        item2.setAdapter(dataAdapter3);
        item3.setAdapter(dataAdapter4);
        item4.setAdapter(dataAdapter5);
        item5.setAdapter(dataAdapter6);

        shopName.setOnItemSelectedListener(this);
        location.setOnItemSelectedListener(this);
        item1.setOnItemSelectedListener(this);
        item2.setOnItemSelectedListener(this);
        item3.setOnItemSelectedListener(this);
        item4.setOnItemSelectedListener(this);
        item5.setOnItemSelectedListener(this);





        generateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> Receipt = new ArrayList<>();

                String valueOfShopName = shopName.getSelectedItem().toString();
                Receipt.add(valueOfShopName);
                String valueOfItem1 = item1.getSelectedItem().toString();
                Receipt.add(valueOfItem1);
                String valueOfItem2 = item2.getSelectedItem().toString();
                Receipt.add(valueOfItem2);
                String valueOfItem3 = item3.getSelectedItem().toString();
                Receipt.add(valueOfItem3);
                String valueOfItem4 = item4.getSelectedItem().toString();
                Receipt.add(valueOfItem4);
                String valueOfItem5 = item5.getSelectedItem().toString();
                Receipt.add(valueOfItem5);
                String valueOfLocation = location.getSelectedItem().toString();
                Receipt.add(valueOfLocation);


                json = jsonCreator.jsonCreate(Receipt);
                String debug = json.toString();
                System.out.println(json.toString());
            }
        });

    }



    @Override
    public void onItemSelected (AdapterView < ? > parent, View view, int position, long id){
        // On selecting a spinner item



    }
    public void onNothingSelected (AdapterView < ? > arg0){
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }


    @Override
    public void onNdefPushComplete(NfcEvent event) {

        final String eventString = "onNdefPushComplete\n" + event.toString();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        eventString,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String jsonMessage = "";
        try {
            jsonMessage = json.toString(2);
        }catch (Exception e){

        }

        byte[] bytesOut = jsonMessage.getBytes();

        NdefRecord ndefRecordOut = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA,
                "text/plain".getBytes(),
                new byte[] {},
                bytesOut);

        return new NdefMessage(ndefRecordOut);
    }

}
