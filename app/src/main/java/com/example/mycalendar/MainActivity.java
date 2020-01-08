package com.example.mycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    DialogFactory dialogFactory;
    Button btn;
    RelativeLayout layout_root;

    EditText edtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.btn);
        layout_root = findViewById(R.id.layout_root);
        edtDate = findViewById(R.id.edt_calendar);
        dialogFactory = new DialogFactory(MainActivity.this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });
     
    }

    private void showCalendarDialog() {

        dialogFactory.createCalendarDialog(new DialogFactory.DialogFactoryInteraction() {
            @Override
            public void onAcceptButtonClicked(String... params) {

                String date = params[0];
                edtDate.setText(date);

            }

            @Override
            public void onDeniedButtonClicked(boolean bool) {

            }
        }, layout_root);
    }
}
