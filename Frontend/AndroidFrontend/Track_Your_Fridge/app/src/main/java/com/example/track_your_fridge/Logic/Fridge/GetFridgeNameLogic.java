package com.example.track_your_fridge.Logic.Fridge;

import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.Logic.IVolleyListener;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;

public class GetFridgeNameLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;
    boolean isReceived = false;

    public GetFridgeNameLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean getFridgeName(String username){
        String url ="http://10.0.2.2:8080/fridge/user/"+username;
        serverRequest.sendToServer(url, null, "GET", false);
        return isReceived;
    }
    @Override
    public void onSuccess(String s) throws JSONException {
        if(!s.equals("[]")) {
            r.showText(s);
        }
    }

    @Override
    public void onError(String s) throws JSONException {
        String a = s;
    }
}
