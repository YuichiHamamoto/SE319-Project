package com.example.track_your_fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.track_your_fridge.Logic.Fridge.AddFridgeLogic;
import com.example.track_your_fridge.Logic.Fridge.GetFridgeNameLogic;
import com.example.track_your_fridge.Logic.InTheFridge.GetFoodsLogic;
import com.example.track_your_fridge.Network.ServerRequest;

import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity implements IView{

    Button bt_fri1,bt_fri2,bt_fri3,bt_add;
    EditText friname;
    TextView fri1name,fri2name,fri3name,tv_alert;
    String f1,f2,f3;
    GetFridgeNameLogic getLogic;
    AddFridgeLogic addLogic;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        getSupportActionBar().setTitle("Fridge");

        bt_fri1 = findViewById(R.id.buttonFri1);
        bt_fri2 = findViewById(R.id.buttonFri2);
        bt_fri3 = findViewById(R.id.buttonFri3);
        bt_add = findViewById(R.id.buttonAdd);

        friname = findViewById(R.id.editTextFridge);

        fri1name = findViewById(R.id.textView);
        fri2name = findViewById(R.id.textView2);
        fri3name = findViewById(R.id.textView3);
        tv_alert = findViewById(R.id.textViewAlert);

        f1= "";
        f2= "";
        f3= "";

        getFridgeName();

    }

    public void getFridgeName(){
        ServerRequest serverRequest = new ServerRequest();
        getLogic = new GetFridgeNameLogic(this,serverRequest);
        tryGetFridgeName(LoginActivity.username,getLogic);
    }

    public boolean tryGetFridgeName(String username, GetFridgeNameLogic logic){
        return logic.getFridgeName(username);
    }

    public void setFridgeNames(){
        fri1name.setText("Fridge 1: "+f1);
        fri2name.setText("Fridge 2: "+f2);
        fri3name.setText("Fridge 3: "+f3);
    }

    public void moveToFri1(View view){
        if(count<1){

        }
        else {
            InTheFridgeActivity.friname = f1;
            Intent in = new Intent(FridgeActivity.this, InTheFridgeActivity.class);
            startActivity(in);
        }
    }
    public void moveToFri2(View view){
        if (count < 2) {

        }
        else {
            InTheFridgeActivity.friname = f2;
            Intent in = new Intent(FridgeActivity.this, InTheFridgeActivity.class);
            startActivity(in);
        }
    }
    public void moveToFri3(View view){
        if(count < 3){

        }
        else {
            InTheFridgeActivity.friname = f3;
            Intent in = new Intent(FridgeActivity.this, InTheFridgeActivity.class);
            startActivity(in);
        }
    }

    public void addFridge(View view){
        String name = friname.getText().toString();
        if(name.length()==0){
            tv_alert.setText("Fill the fridge neme here");
            tv_alert.setVisibility(View.VISIBLE);
        }
        else {
            if (count == 0) {
                f1 = name;
                addFridge(name);
                count++;
            } else if (count == 1) {
                f2 = name;
                addFridge(name);
                count++;
            } else if (count == 2) {
                f3 = name;
                addFridge(name);
                count++;
            } else if (count == 3) {
                tv_alert.setText("You can have no more than 3 fridge");
                tv_alert.setVisibility(View.VISIBLE);
            }

            setFridgeNames();
        }
    }

    public void addFridge(String friname){
        ServerRequest serverRequest = new ServerRequest();
        addLogic = new AddFridgeLogic(this,serverRequest);
        tryAddFridge(LoginActivity.username,friname,addLogic);
    }
    public boolean tryAddFridge(String username, String friname, AddFridgeLogic logic){
        return logic.addFridge(username,friname);
    }
    @Override
    public void showText(String s) {
        s= s.substring(1,s.length()-1);
        String [] parts = s.split(",");
        if(parts.length==1){
            f1=parts[0].substring(1,parts[0].length()-1);
            count = 1;
        }
        else if(parts.length==2){
            f1=parts[0].substring(1,parts[0].length()-1);
            f2=parts[1].substring(1,parts[1].length()-1);
            count = 2;
        }
        else if(parts.length==3){
            f1=parts[0].substring(1,parts[0].length()-1);
            f2=parts[1].substring(1,parts[1].length()-1);
            f3=parts[2].substring(1,parts[2].length()-1);
            count = 3;
        }
        setFridgeNames();
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}