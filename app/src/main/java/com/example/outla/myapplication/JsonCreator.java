package com.example.outla.myapplication;

import android.nfc.NfcAdapter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class JsonCreator {

    NfcAdapter nfcAdapter;

    public JSONObject jsonCreate(List<String> receipts) {

        String a = receipts.get(0);
        JSONObject shopInfo = ShopInfo(a);
        a = receipts.get(1);
        JSONObject item1 = items(a);
        a = receipts.get(2);
        JSONObject item2 = items(a);
        a = receipts.get(3);
        JSONObject item3 = items(a);
        a = receipts.get(4);
        JSONObject item4 = items(a);
        a = receipts.get(5);
        JSONObject item5 = items(a);
        a = receipts.get(6);
        JSONObject location = location(a);





        JSONObject receiptObj = new JSONObject();

        JSONArray items = new JSONArray();
        items.put(item1);
        items.put(item2);
        items.put(item3);
        items.put(item4);
        items.put(item5);



        try {

            receiptObj.put("shopInfo",shopInfo);
            receiptObj.put("items", items);
            receiptObj.put("location", location);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        float[] Totals = totals(receiptObj);
        try {


            receiptObj.put("SubTotal",String.format("%.02f", Totals[0]));
            receiptObj.put("TaxTotal",String.format("%.02f", Totals[1]));
            receiptObj.put("Total",String.format("%.02f", Totals[2]));
            receiptObj.put("Cash",String.format("%.02f", Totals[2]));
            receiptObj.put("ChangeDue",String.format("%.02f", 0.00f));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(receiptObj);
        return receiptObj;
    }

    private float[] totals(JSONObject items){
        Float total = 0f;
        try {
            JSONArray object = items.getJSONArray("items");
            JSONObject item = null;
            for(int i =0;i<object.length();i++) {
                 item = object.getJSONObject(i);
                 String shopPrice = item.getString("totalPrice");
                Float f= Float.parseFloat(shopPrice);
                total = total + f;
            }


            System.out.println(total);
        }catch (Exception e){
            Log.e("my app","the json cannot be converted");
        }
        float totals[] = new float[3];
         totals[0] = total;
         totals[1] = total/4;
         totals[2] = totals[0] + totals[1];
        return totals;
    }

    private JSONObject items(String a) {

        String itemName;
        String itemCategory;
        String itemPrice;
        String quantity;
        String totalPrice;
        String addInfo;


        switch (a) {
            case "Bread":
                itemName = "Bread";
                itemCategory = "Bakery";
                itemPrice = "1.50";
                quantity = "3";
                totalPrice = "4.00";
                addInfo = "@3 for €4.00";

                break;
            case "Jelly":
                itemName = "Jelly";
                itemCategory = "Confectionary";
                itemPrice = "2.00";
                quantity = "2";
                totalPrice = "4.00";
                addInfo = "null";

                break;
            case "Burger":
                itemName = "Burger";
                itemCategory = "Meat";
                itemPrice = "2.50";
                quantity = "4";
                totalPrice = "7.50";
                addInfo = "@Buy 3 get one Free!";

                break;
            case "Soap":
                itemName = "Soap";
                itemCategory = "Cleaning";
                itemPrice = "1.50";
                quantity = "3";
                totalPrice = "3.00";
                addInfo = "@3 for €3.00";

                break;
            case "Flowers":
                itemName = "Flowers";
                itemCategory = "Plants";
                itemPrice = "10.00";
                quantity = "1";
                totalPrice = "10.00";
                addInfo = "null";

                break;
            case "Mince":
                itemName = "Mince";
                itemCategory = "Meat";
                itemPrice = "4.00";
                quantity = "3";
                totalPrice = "4.00";
                addInfo = "@3 for €4.00";

                break;
            case "Pancakes":
                itemName = "Pancakes";
                itemCategory = "Confectionery";
                itemPrice = "3.50";
                quantity = "1";
                totalPrice = "3.50";
                addInfo = "null";

                break;
            case "Chicken Breast":
                itemName = "Chicken Breast";
                itemCategory = "Meat";
                itemPrice = "2";
                quantity = "5";
                totalPrice = "10";
                addInfo = "null";

                break;
            case "Wine":
                itemName = "Wine";
                itemCategory = "Alcohol";
                itemPrice = "15";
                quantity = "1";
                totalPrice = "15";
                addInfo = "null";

                break;
            case "Beer":
                itemName = "Beer";
                itemCategory = "Alcohol";
                itemPrice = "2.50";
                quantity = "8";
                totalPrice = "15";
                addInfo = "@8 for €15.00";

                break;
            case "Muffins":
                itemName = "Muffins";
                itemCategory = "Bakery";
                itemPrice = "1.00";
                quantity = "3";
                totalPrice = "3.00";
                addInfo = "null";

                break;
            case "Chocolate":
                itemName = "Chocolate";
                itemCategory = "Confectionery";
                itemPrice = "1.00";
                quantity = "2";
                totalPrice = "2.00";
                addInfo = "null";

                break;
            case "7up":
                itemName = "7up";
                itemCategory = "Soft drinks";
                itemPrice = "1.00";
                quantity = "2";
                totalPrice = "2.00";
                addInfo = "null";

                break;
            case "Bananas":
                itemName = "Bananas";
                itemCategory = "Fruit";
                itemPrice = "0.80";
                quantity = "3";
                totalPrice = "2.00";
                addInfo = "@3 for €2.00";

                break;
            case "Apple":
                itemName = "Apple";
                itemCategory = "Fruit";
                itemPrice = "1.00";
                quantity = "5";
                totalPrice = "5.00";
                addInfo = "null";

                break;
            case "Eclair":
                itemName = "Eclair";
                itemCategory = "Bakery";
                itemPrice = "1.50";
                quantity = "3";
                totalPrice = "3.00";
                addInfo = "@3 for €3.00";

                break;
            case "Birthday Cake":
                itemName = "Birthday Cake";
                itemCategory = "Bakery";
                itemPrice = "15.00";
                quantity = "1";
                totalPrice = "15.00";
                addInfo = "null";

                break;
            case "Mi wadi":
                itemName = "Mi wadi";
                itemCategory = "Soft drinks";
                itemPrice = "1.00";
                quantity = "1";
                totalPrice = "1.00";
                addInfo = "null";

                break;
            case "Biscuits":
                itemName = "Biscuits";
                itemCategory = "Confectionary";
                itemPrice = "3.00";
                quantity = "1";
                totalPrice = "3.00";
                addInfo = "null";

                break;
            case "Pringles":
                itemName = "Pringles";
                itemCategory = "Confectionary";
                itemPrice = "2.00";
                quantity = "1";
                totalPrice = "2.00";
                addInfo = "null";

                break;
            case "Wipes":
                itemName = "Wipes";
                itemCategory = "Cleaning";
                itemPrice = "5.00";
                quantity = "1";
                totalPrice = "5.00";
                addInfo = "null";

                break;
            case "Bleach":
                itemName = "Bleach";
                itemCategory = "Cleaning";
                itemPrice = "4.00";
                quantity = "1";
                totalPrice = "4.00";
                addInfo = "null";

                break;
            case "Washing Liquid":
                itemName = "Washing Liquid";
                itemCategory = "Cleaning";
                itemPrice = "2.00";
                quantity = "2";
                totalPrice = "4.00";
                addInfo = "null";

                break;
            case "Flour":
                itemName = "Flour";
                itemCategory = "Baking";
                itemPrice = "1.00";
                quantity = "1";
                totalPrice = "1.00";
                addInfo = "null";

                break;
            default:
                itemName = "Washing Liquid";
                itemCategory = "Cleaning";
                itemPrice = "2.00";
                quantity = "2";
                totalPrice = "4.00";
                addInfo = "null";

                break;
        }


            JSONObject item = new JSONObject();
            try {
                item.put("itemName", itemName);
                item.put("itemCategory", itemCategory);
                item.put("itemPrice", itemPrice);
                item.put("quantity", quantity);
                item.put("totalPrice", totalPrice);
                item.put("addInfo", addInfo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return item;


    }

    private JSONObject ShopInfo (String a){


        String shopName;
        String shopId;
        String vatNum;
        String message1;
        String message2;
        String message3;
        String barcode;
        String shopLogo;
        String uniqueId;
        String timeDate;

        switch (a) {
            case "Tesco":
                shopName = "Tesco";
                shopId = "1231243241";
                vatNum = "1234567";
                message1 = "Every Little Helps";
                message2 = "Thank you for shopping in Tesco today";
                message3 = "you could have claimed 10 clubcard points by shopping today";
                barcode = "Tesco_barcode.png";
                shopLogo = "Tesco_logo.png";
                uniqueId = UUID.randomUUID().toString();
                timeDate =  new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                        Locale.getDefault()).format(System.currentTimeMillis());


                break;
            case "Dunnes":
                shopName = "Dunnes";
                shopId = "1231243249";
                vatNum = "7654321";
                message1 = "Always better Value";
                message2 = "TOP UP YOUR PHONE AT ANY TILL!";
                message3 = "Refunds are valid for 10 days following purchase with copy of receipt";
                barcode = "Dunnes_barcode.png";
                shopLogo = "Dunnes_logo.png";
                uniqueId = UUID.randomUUID().toString();
                timeDate =  new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                        Locale.getDefault()).format(System.currentTimeMillis());

                break;
            case "Lidl":
                shopName = "Lidl";
                shopId = "1231243141";
                vatNum = "1569234";
                message1 = "Big on quality, lidl on price";
                message2 = "Thank you for shopping in lidl today";
                message3 = "Come back soon!";
                barcode = "Lidl_barcode.png";
                shopLogo = "Lidl_logo.png";
                uniqueId = UUID.randomUUID().toString();
                timeDate =  new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                        Locale.getDefault()).format(System.currentTimeMillis());

                break;
            case "Aldi":
                shopName = "Aldi";
                shopId = "1231232241";
                vatNum = "1567234";
                message1 = "Helping you shop differently";
                message2 = "Thank you for shopping in Aldi today";
                message3 = "you could have claimed 10 clubcard points by shopping today";
                barcode = "Aldi_barcode.png";
                shopLogo = "Aldi_logo.png";
                uniqueId = UUID.randomUUID().toString();
                timeDate =  new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                        Locale.getDefault()).format(System.currentTimeMillis());

                break;
            default:
                shopName = "McDonalds";
                shopId = "1321232241";
                vatNum = "1568234";
                message1 = "im lovin it!";
                message2 = "Thank you for Eating McDonalds";
                message3 = "";
                barcode = "McDonalds_barcode.png";
                shopLogo = "McDonalds_logo.png";
                uniqueId = UUID.randomUUID().toString();
                timeDate =  new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                        Locale.getDefault()).format(System.currentTimeMillis());

                break;
            }

            JSONObject person = new JSONObject();
            try {
                person.put("shopName", shopName);
                person.put("shopId", shopId);
                person.put("vatNum", vatNum);
                person.put("message1", message1);
                person.put("message2", message2);
                person.put("message3", message3);
                person.put("shopLogo", shopLogo);
                person.put("barcode", barcode);
                person.put("uuid", uniqueId);
                person.put("timeDate", timeDate);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return person;
        }

    private JSONObject uuid (String uuid){
        JSONObject UUID = new JSONObject();
        try {
            UUID.put("uuid", uuid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return UUID;
    }

    private JSONObject location (String a){


        String location;
        String address;
        String phoneNumber;

        switch (a) {
            case "Parnell Street":
                location = "Parnell Street";
                address = "13 Parnell street";
                phoneNumber = "0857273745";

                break;
            case "Newbridge":
                location = "Newbridge";
                address = "4a Main street";
                phoneNumber = "0857273732";

                break;
            case "Central Park":
                location = "Central Park";
                address = "Building 4";
                phoneNumber = "0827273732";

                break;
            case "Stephens Green":
                location = "Stephens Green";
                address = "unit 4 shopping centre";
                phoneNumber = "0857232732";

                break;
            default:
                location = "Grafton Street";
                address = "unit 18 shopping centre";
                phoneNumber = "0857273876";
                break;
        }

        JSONObject Address = new JSONObject();
        try {
            Address.put("Location", location);
            Address.put("Address", address);
            Address.put("PhoneNumber", phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Address;
    }
}

