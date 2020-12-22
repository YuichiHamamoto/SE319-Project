package com.example.track_your_fridge.Logic.InTheFridge;

import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.Logic.IVolleyListener;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddFoodLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;

    public AddFoodLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean addFood(String foodname, String amount, String unit,String fridgename){
        String url ="http://10.0.2.2:8080/food/add";
        final JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("foodname", foodname);
            jsonObject.put("exp", "12012020");
            jsonObject.put("amount", amount);
            jsonObject.put("unit", unit);
            jsonObject.put("fridgename", fridgename);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        serverRequest.sendToServer(url, jsonObject, "POST", true);
        return true;
    }
    @Override
    public void onSuccess(String s) throws JSONException {

    }

    @Override
    public void onError(String s) throws JSONException {

    }
}
