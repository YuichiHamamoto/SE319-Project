package com.example.track_your_fridge.Logic;

import org.json.JSONException;

public interface IVolleyListener {
    public void onSuccess(String s) throws JSONException;
    public void onError(String s)throws JSONException;
}
