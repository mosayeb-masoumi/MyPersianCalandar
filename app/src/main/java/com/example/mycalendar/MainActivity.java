package com.example.mycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker np_year,np_month,np_day;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.btn);


        np_year =findViewById(R.id.np_year);
        np_month =findViewById(R.id.np_month);
        np_day =findViewById(R.id.np_day);

        np_month.setDisplayedValues( new String[]{ "01", "02","03","04", "05","06", "07", "08","09","10", "11","12"} );
        np_day.setDisplayedValues( new String[]{ "01", "02","03","04", "05","06", "07", "08","09","10", "11","12",
                "13", "14","15","16", "17","18", "19", "20","21","22", "23","24",
                "25", "26","27","28", "29","30", "31"} );


        SolarCalendar calendar = new SolarCalendar();
        int year = Integer.parseInt(calendar.getCurrentShamsiYear());
        int month = Integer.parseInt(calendar.getCurrentShamsiMonth());
        int day = Integer.valueOf(calendar.getCurrentShamsiDay());

        np_year.setMinValue(year-1);
        np_year.setMaxValue(year+1);

        np_month.setMinValue(1);
        np_month.setMaxValue(12);

        np_day.setMinValue(1);
        np_day.setMaxValue(31);


        //init set
        np_year.setValue(year);
        np_month.setValue(month);
        np_day.setValue(day);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String month = String.valueOf(String.valueOf(np_month.getValue()).length() < 2 ? "0" + np_month.getValue() : np_month.getValue());
                String day = String.valueOf(String.valueOf(np_day.getValue()).length() < 2 ? "0" + np_day.getValue() : np_day.getValue());


                String date = np_year.getValue() +"/"+ month +"/"+ day;
                String a = date;
            }
        });



    }
}
