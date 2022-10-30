package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdActivity extends AppCompatActivity {

    TextView date, temperature,desc, city, temp_maks,temp_min, humidity, pressure;
    ImageView icon;
    Button anotherCity;

    private final static  String apikey = "856a9bb5a952b44aebf33ffe336f6d97"; //api key
    private final static  String base_url = "https://api.openweathermap.org/data/2.5/"; //url api openweathermap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        city = findViewById(R.id.cityName);
        date = findViewById(R.id.date);
        temperature = findViewById(R.id.temperature);
        temp_maks = findViewById(R.id.temp_maks);
        temp_min = findViewById(R.id.temp_min);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.presure);
        desc = findViewById(R.id.desc);
        icon = findViewById(R.id.icon);
        anotherCity = findViewById(R.id.findAnotherCity);

        //get intent dari secondactivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.MESSAGE);
        city.setText(message);

        //menampilkan tanggal
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = format.format(new Date());
        date.setText(currentDate);

        //memanggil method getWeathers
        getWeathers();

        //berpindah ke secondactivity
        anotherCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getWeathers(){
        //API CALL BASE URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api myApi = retrofit.create(Api.class);

        //Call data dari API
        Call<WeatherMain> caller = myApi.getweather(city.getText().toString().trim(), apikey);
        caller.enqueue(new Callback<WeatherMain>() {
            @Override
            public void onResponse(Call<WeatherMain> call, Response<WeatherMain> response) {
                //Jika kota tidak ada maka akan muncul toast
                if(response.code()==404){
                    Toast.makeText(ThirdActivity.this, "Please Enter a valid City", Toast.LENGTH_LONG).show();
                } //Jika response unsuccessful maka akan memunculkan toast error code
                else if(!(response.isSuccessful())){
                    Toast.makeText(ThirdActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }

                //get response dari API
                WeatherMain main = response.body();

                //get data main yang ada pada kelas WeatherData
                WeatherData weatherData = main.getMain();

                //temperature
                Double temp = weatherData.getTemp();
                Integer temperatures = (int)(temp-273.15);
                temperature.setText(String.valueOf(temperatures)+"\u00B0"+"C");

                //temperature maksimal
                Double temp_max = weatherData.getTempMax();
                Integer tempMaks = (int)(temp_max-273.15);
                temp_maks.setText(String.valueOf(tempMaks)+"\u00B0"+"C");

                //temperature minimal
                Double temp_mins = weatherData.getTempMin();
                Integer tempMin = (int)(temp_mins-273.15);
                temp_min.setText(String.valueOf(tempMin)+"\u00B0"+"C");

                //humidity
                humidity.setText(String.valueOf(weatherData.getHumidity()));

                //pressure
                pressure.setText(String.valueOf(weatherData.getPressure()));

                //get data weather yang ada pada kelas Weathers
                List<Weathers> description = main.getWeathers();

                //akses data pada kelas Weathers
                for (Weathers data : description){
                    //deskripsi cuaca
                    desc.setText(data.getDesc());

                    //mendapatkan icon cuaca
                    String icons = data.getIcon();

                    //menampilkan icon cuaca
                    String iconUrl = "https://openweathermap.org/img/wn/"+icons+"@2x.png";
                    Glide.with(ThirdActivity.this)
                            .load(iconUrl)
                            .into(icon);
                }

            }

            @Override
            public void onFailure(Call<WeatherMain> call, Throwable t) {
                Toast.makeText(ThirdActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}