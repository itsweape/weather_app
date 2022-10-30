package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText cityInput;
    public static final String MESSAGE = "Message_Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        cityInput = findViewById(R.id.cityInput);
    }

    //berpindah ke thirdactivity
    public void findCityWeather(View view){
        Intent intent = new Intent(view.getContext(), ThirdActivity.class);
        String message = cityInput.getText().toString();
        intent.putExtra(MESSAGE, message);
        startActivity(intent);
    }
}