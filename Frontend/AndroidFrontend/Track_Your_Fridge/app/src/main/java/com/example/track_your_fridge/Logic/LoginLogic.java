package com.example.track_your_fridge.Logic;


import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.LoginActivity;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;

/**
 * @author Yuichi Hamamoto
 */
public class LoginLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;
    String password;
    boolean isLogedin = false;

    /**
     *
     * @param r
     * Given IVeiw
     * @param serverRequest
     * Given serverRequest
     */
    public LoginLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * Let user login
     * @param username
     * Given username
     * @param password
     * Given password
     * @return
     * @throws JSONException
     */
    public boolean login(String username,String password) throws JSONException {
        this.password = password;
        String url ="http://10.0.2.2:8080/user/"+username;
        serverRequest.sendToServer(url, null, "GET", true);
        return isLogedin;
    }


    /**
     * onSuccess method called in the server request
     * @param response
     * Given response in the serverRequest
     */
    @Override
    public void onSuccess(String response) {
        if(response.equals("null")){
            r.showText("Wrong password or username entered");
        }
        else {
            String[] parts = response.split(",");
            String[] partsPass = parts[1].split(":");
            String password = partsPass[1].substring(1, partsPass[1].length() - 1);

            if (!this.password.isEmpty() && this.password.equals(password)) {
                r.showText("You are logged in!");
                r.switchActivity(0);
                String[] partsEmail = parts[2].split(":");
                LoginActivity.email = partsEmail[1].substring(1, partsEmail[1].length() - 1);
                isLogedin = true;
            } else {
                r.showText("Wrong password or username entered");
            }
        }


    }

    /**
     * onError method called in the server request
     * @param errorMessage
     * Given error message in the server request
     */
    @Override
    public void onError (String errorMessage) {
        r.showText(errorMessage);
    }



}
