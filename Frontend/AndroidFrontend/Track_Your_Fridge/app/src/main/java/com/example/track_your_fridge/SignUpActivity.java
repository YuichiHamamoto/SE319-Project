package com.example.track_your_fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.track_your_fridge.Logic.SignUpLogic;
import com.example.track_your_fridge.Network.ServerRequest;

public class SignUpActivity extends AppCompatActivity implements IView{
    Button btn_signup, btn_login;
    EditText et_user, et_password, et_passVer,et_email;
    TextView tv_user,tv_password, tv_passVer,tv_email, tv_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");

        btn_login = findViewById(R.id.buttonLogin);
        btn_signup = findViewById(R.id.buttonSignUp);

        et_user = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword);
        et_passVer = findViewById(R.id.editTextPasswordVer);
        et_email = findViewById(R.id.editTextEmail);

        tv_user = findViewById(R.id.textViewUsername);
        tv_password = findViewById(R.id.textViewPassword);
        tv_passVer = findViewById(R.id.editTextPasswordVer);
        tv_email = findViewById(R.id.textViewEmail);
        tv_alert = findViewById(R.id.textViewAlert);


    }

    public void signUp(View view){
        ServerRequest serverRequest = new ServerRequest();
        SignUpLogic logic = new SignUpLogic(this, serverRequest);

        String username = et_user.getText().toString();
        String password = et_password.getText().toString();
        String passwordVer = et_passVer.getText().toString();
        String email = et_email.getText().toString();

        trySignUp(username,password,passwordVer,email,logic);
    }

    /**
     * Support method of signUp()
     * @param username
     * Given username
     * @param password
     * Given password
     * @param passwordVer
     * Given password to verify the password
     * @param email
     * Given email
     * @param logic
     * Given logic
     * @return
     * Indicates is the user successfully signed up
     */
    public boolean trySignUp(String username, String password, String passwordVer, String email, SignUpLogic logic){
        logic.signup(username,password,passwordVer,email);
        return false;
    }

    /**
     * Jump back to Login view
     * @param view
     * Given view
     */
    public void moveToLogin(View view){
        Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(in);
    }

    @Override
    public void showText(String s) {
        tv_alert.setText(s);
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {
        Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(in);
    }
}