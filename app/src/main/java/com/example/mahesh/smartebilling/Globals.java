package com.example.mahesh.smartebilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by MANDEEP on 2/25/2017.
 */

public class Globals {
    public static ArrayList<HashMap<String,String>> cart = new ArrayList<>();
    public static ArrayList rareAndPrevious = new ArrayList();

    public static String username = "NULL";
    public static int userid = -1;
    public static String billid = "1234567890";
    public static float purchase_amt;
    public static int visits;
    public static String IP = "10.0.3.2";     //for genymotion

    public static String generateBillId(){
        StringBuilder bill = new StringBuilder();
        bill.append(userid).append(new java.util.Date()).append(username);
        return bill.toString().replaceAll("\\s","").toLowerCase().replaceAll(":","");
    }
    public static ArrayList<String> getProducts() {
        ArrayList<String> data = new ArrayList<>(cart.size());
        int i = 0;
        for(HashMap<String, String> item : Globals.cart){
            data.add(item.get("product_name"));
        }
        return data;
    }

    public static int getTotalPriceOnCart() {
        int total = 0;
        for(HashMap<String, String> item : Globals.cart){
            total += Integer.parseInt(item.get("product_price"))*Integer.parseInt(item.get("product_count"));
        }
        return total;
    }

    public static float getDiscount() {
        float discount = 0.0f;
        float purchase_amt = Globals.purchase_amt;
        int visits = Globals.visits;

        if(purchase_amt>5000 && visits>=1){
            discount = 5;
        }else if(purchase_amt<=5000 && purchase_amt>3000 && visits>=2){
            discount = 2;
        }else if(purchase_amt<=3000 && purchase_amt>2000 && visits>=2){
            discount = 2;
        }else if(purchase_amt<=2000 && purchase_amt>1000 && visits>=1){
            discount = 1;
        }

        return discount; //in percentage
    }

    public static int[] getProductsID() {
        int[] ids = new int[cart.size()];
        int i = 0;
        for(HashMap<String, String> item : Globals.cart){
            ids[i++] = Integer.parseInt(item.get("product_id"));
        }
        return ids;
    }

    public static ArrayList<Integer> getCounts() {
        ArrayList<Integer> counts = new ArrayList<>(cart.size());
        int i = 0;
        for(HashMap<String, String> item : Globals.cart){
            counts.add(Integer.parseInt(item.get("product_count")));
        }
        return counts;
    }

    public static ArrayList<Float> getPrices() {
        ArrayList<Float> prices = new ArrayList<>(cart.size());
        int i = 0;
        for(HashMap<String, String> item : Globals.cart){
            prices.add(Float.parseFloat(item.get("product_price")));
        }
        return prices;
    }

    public static ArrayList<Float> getWeights() {
        ArrayList<Float> weights = new ArrayList<>(cart.size());
        int i = 0;
        for(HashMap<String, String> item : Globals.cart){
            weights.add(Float.parseFloat(item.get("product_weight")));
        }
        return weights;
    }
}
