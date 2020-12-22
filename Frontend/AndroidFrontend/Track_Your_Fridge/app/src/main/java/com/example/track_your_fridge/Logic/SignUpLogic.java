package com.example.track_your_fridge.Logic;

import com.example.track_your_fridge.IView;
import com.example.track_your_fridge.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SignUpLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;
    int count;
    String password;
    String username;
    String email;

    /**
     *
     * @param r
     * Given IView
     * @param serverRequest
     * Given serverRequest
     */
    public SignUpLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
        count = 0;
    }

    /**
     * Check if the username is available
     * @param username
     * Given username
     * @param password
     * Given password
     * @param passwordVer
     * Given password to verify
     * @param email
     * Given email
     */
    public void signup(String username,String password, String passwordVer,String email) {
        if(!password.equals(passwordVer)){
            r.showText("Password doesn't match");
        }
        else {
            String url = "http://10.0.2.2:8080/user/" + username;
            this.password = password;
            this.username = username;
            this.email = email;
            serverRequest.sendToServer(url, null, "GET", true);
        }
    }

    /**
     * Let user sign up for a new account
     * @throws JSONException
     */
    public void signupPost() throws JSONException {

        if(password.length()<8){
            r.showText("Password has to have at least 8 words");
        }
        else{
            String url ="http://10.0.2.2:8080/user/add";
            final JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("username", username);
                jsonObject.put("password",password);
                jsonObject.put("email", email);

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            serverRequest.sendToServer(url, jsonObject, "POST", true);
        }
    }

    /**
     *onSuccess method called in the server request
     * @param s
     * Given String
     * @throws JSONException
     */
    @Override
    public void onSuccess(String s) throws JSONException {
        if(count == 0){
            count++;
            if(s.equals("null")){
                this.signupPost();
            }
            else{
                r.showText("The username is taken");
            }

        }
        else if(count==1){
            r.showText("Success");
            r.switchActivity(1);
        }
    }

    /**
     *onError method called in the server request
     * @param s
     * Given String
     */
    @Override
    public void onError(String s) throws JSONException {
        if(count == 0){
            count++;
            this.signupPost();
        }
        else {
            r.showText("The email is taken");
        }
    }
}
