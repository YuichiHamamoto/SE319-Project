package com.example.track_your_fridge.Network;

import com.example.track_your_fridge.Logic.IVolleyListener;
import org.json.JSONObject;

public interface IServerRequest {
    public void sendToServer(String url, JSONObject newUserObj, String methodType, Boolean isJson);
    public void addVolleyListener(IVolleyListener logic);
}
