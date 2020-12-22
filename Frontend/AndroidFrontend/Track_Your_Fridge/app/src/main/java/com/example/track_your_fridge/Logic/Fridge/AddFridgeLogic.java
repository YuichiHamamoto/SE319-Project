package com.example.track_your_fridge.Logic.Fridge;

import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.Logic.IVolleyListener;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddFridgeLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;

    
    public AddFridgeLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean addFridge(String username,String friname){
        String url ="http://10.0.2.2:8080/fridge/add";
        final JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("fridgename", friname);
            jsonObject.put("username", username);

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
