package com.example.track_your_fridge.Logic.InTheFridge;

import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.Logic.IVolleyListener;
import com.example.track_your_fridge.LoginActivity;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;

public class GetFoodsLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;
    boolean isReceived = false;

    public GetFoodsLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean getFoods(String fridgeName){
        String url ="http://10.0.2.2:8080/food/fridge/"+fridgeName;
        serverRequest.sendToServer(url, null, "GET", false);
        return isReceived;
    }

    @Override
    public void onSuccess(String s) throws JSONException {
        if(s.equals("[]")){
        }
        else {
            r.showText(s);
            isReceived = true;
        }
    }

    @Override
    public void onError(String s) throws JSONException {

    }
}
