package com.example.track_your_fridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.track_your_fridge.Logic.InTheFridge.AddFoodLogic;
import com.example.track_your_fridge.Logic.InTheFridge.Food;
import com.example.track_your_fridge.Logic.InTheFridge.GetFoodsLogic;
import com.example.track_your_fridge.Logic.InTheFridge.InFridgeAdapter;
import com.example.track_your_fridge.Logic.InTheFridge.Unit;
import com.example.track_your_fridge.Network.ServerRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class InTheFridgeActivity extends AppCompatActivity implements IView {
    ArrayList<Food> list;
    RecyclerView rcview;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    EditText et_food,et_amount,et_remove,et_amountE,et_indexE;
    Button bt_insert,bt_remove,bt_edit;
    Spinner unit;
    GetFoodsLogic getLogic;
    AddFoodLogic addLogic;
    public static String friname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_the_fridge);
        getSupportActionBar().setTitle("In The Fridge");

        list = new ArrayList<>();

        et_food = findViewById(R.id.editTextFood);
        et_amount = findViewById(R.id.editTextAmount);
        et_remove = findViewById(R.id.editTextRemove);
        et_amountE = findViewById(R.id.editTextAmountEdit);
        et_indexE = findViewById(R.id.editTextIndexEdit);

        bt_insert = findViewById(R.id.buttonInsert);
        bt_remove = findViewById(R.id.buttonRemove);
        bt_edit = findViewById(R.id.buttonEdit);

        unit = findViewById(R.id.spinnerUnit);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(InTheFridgeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit.setAdapter(arrayAdapter);

        getFoods();

//        createFoodList();
//        buildRecyclerView();
    }

    public void getFoods(){
        ServerRequest serverRequest = new ServerRequest();
        getLogic = new GetFoodsLogic(this,serverRequest);
        tryGetFoods(friname,getLogic);
    }
    public boolean tryGetFoods(String friname, GetFoodsLogic logic){
        return logic.getFoods(friname);
    }
    public void edit(View view){
        int position = Integer.parseInt(et_indexE.getText().toString());
        int amount = Integer.parseInt(et_amountE.getText().toString());
        editItem(position,amount);
    }
    public void insert(View view){
        String name = et_food.getText().toString();
        int amount = Integer.parseInt(et_amount.getText().toString());
        String st = unit.getSelectedItem().toString();

        ServerRequest serverRequest = new ServerRequest();
        addLogic = new AddFoodLogic(this,serverRequest);
        addLogic.addFood(name,Integer.toString(amount),st,friname);

        Unit unit = null;
        if(st.equals("g")){
            unit = Unit.g;
        }
        else if(st.equals("lb")){
            unit = Unit.lb;
        }
        else if(st.equals("ml")){
            unit = Unit.ml;
        }
        else if(st.equals("none")) {
            unit = Unit.none;
        }

        
        insertItem(name,amount,unit);
    }

    public void remove(View view){
        int position = Integer.parseInt(et_remove.getText().toString());
        removeItem(position);
    }

    public void insertItem(String name, int amount, Unit unit){
        list.add(list.size(),new Food(name,amount,unit));
        adapter.notifyItemInserted(list.size());
    }

    public void removeItem(int position){
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void editItem(int position,int amount){
        Food temp = list.get(position);
        String nameTemp = temp.getName();
        Unit unitTemp = temp.getUnit();
        removeItem(position);
        insertItem(nameTemp,amount,unitTemp);
    }
    public void createFoodList(){

        list.add(new Food("tomato",2, Unit.none));
        list.add(new Food("milk", 1000,Unit.ml));
        list.add(new Food("cheese",300, Unit.g));
    }

    public void buildRecyclerView(){
        rcview = findViewById(R.id.recyclerView);
        rcview.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        adapter = new InFridgeAdapter(list);
        rcview.setLayoutManager(manager);
        rcview.setAdapter(adapter);
    }


    @Override
    public void showText(String s) {
        String st = s.substring(1,s.length()-1);
        String [] parts = st.split(",");
        for(int i=0; i<parts.length/6;i++){
            String name = parts[6*i+1].substring(12,parts[6*i+1].length()-1);
            int amount = Integer.parseInt(parts[6*i+3].substring(9));
            Unit unit = null;
            String st_unit = parts[6*i+4].substring(8,parts[6*i+4].length()-1);
            if(st_unit.equals("g")){
                unit = Unit.g;
            }
            else if(st_unit.equals("lb")){
                unit = Unit.lb;
            }
            else if(st_unit.equals("ml")){
                unit = Unit.ml;
            }
            else if(st_unit.equals("none")){
                unit = Unit.none;
            }

            list.add(new Food(name,amount,unit));
        }
        this.buildRecyclerView();

    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}