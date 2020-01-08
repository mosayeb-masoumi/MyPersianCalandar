package com.example.mycalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class DialogFactory {

    private Context context;



    public interface DialogFactoryInteraction {

        void onAcceptButtonClicked(String... strings);

        void onDeniedButtonClicked(boolean cancel_dialog);
    }

    public DialogFactory(Context ctx) {
        this.context = ctx;
    }




    public void createCalendarDialog(DialogFactoryInteraction listener, View view) {
        View customLayout = LayoutInflater.from(context).inflate(R.layout.calendar_dialog, (ViewGroup) view, false);

        //define views inside of dialog
        ImageView img_close = customLayout.findViewById(R.id.img_close);
        Button btn_register = customLayout.findViewById(R.id.btn1);
        Button btn_todayDate = customLayout.findViewById(R.id.btn2);
        NumberPicker np_year = customLayout.findViewById(R.id.np_year);
        NumberPicker np_month = customLayout.findViewById(R.id.np_month);
        NumberPicker np_day = customLayout.findViewById(R.id.np_day);

        SolarCalendar calendar = new SolarCalendar();

        int lastYear_ = (Integer.parseInt(calendar.getCurrentShamsiYear()))-1;
        int currentYear_ = Integer.parseInt(calendar.getCurrentShamsiYear());
        int nextYear_ = (Integer.parseInt(calendar.getCurrentShamsiYear()))+1;

        String lastYear = String.valueOf(lastYear_);
        String currentYear = String.valueOf(currentYear_);
        String nextYear = String.valueOf(nextYear_);

        np_year.setDisplayedValues( new String[]{ ConvertEnDigitToFa.convert(lastYear)
                ,ConvertEnDigitToFa.convert(currentYear) ,ConvertEnDigitToFa.convert(nextYear)} );

        np_month.setDisplayedValues( new String[]{ "۰۱", "۰۲","۰۳","۰۴", "۰۵","۰۶", "۰۷", "۰۸","۰۹","۱۰", "۱۱","۱۲"} );

        np_day.setDisplayedValues( new String[]{ "۰۱", "۰۲","۰۳","۰۴", "۰۵","۰۶", "۰۷", "۰۸","۰۹","۱۰", "۱۱","۱۲",
                "۱۳", "۱۴","۱۵","۱۶", "۱۷","۱۸", "۱۹", "۲۰","۲۱","۲۲", "۲۳","۲۴",
                "۲۵", "۲۶","۲۷","۲۸", "۲۹","۳۰", "۳۱"});

        int year = Integer.parseInt(calendar.getCurrentShamsiYear());
        int month = Integer.parseInt(calendar.getCurrentShamsiMonth());
        int day = Integer.valueOf(calendar.getCurrentShamsiDay());


        np_year.setMinValue(currentYear_-1);
        np_year.setMaxValue(currentYear_+1);

        np_month.setMinValue(1);
        np_month.setMaxValue(12);

        np_day.setMinValue(1);
        np_day.setMaxValue(31);

//        //init set
        np_year.setValue(year);
        np_month.setValue(month);
        np_day.setValue(day);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setView(customLayout);

        //create dialog and set background transparent
        android.app.AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btn_todayDate.setOnClickListener(v -> {
            //init set
            np_year.setValue(year);
            np_month.setValue(month);
            np_day.setValue(day);
        });

        btn_register.setOnClickListener(v -> {

            String year1 = ConvertEnDigitToFa.convert(String.valueOf(np_year.getValue()));
            String month1 = ConvertEnDigitToFa.convert(String.valueOf(np_month.getValue()));
            String month2 =(String.format("%s", month1.length() < 2 ? "۰" + month1 : month1));
            String day1 = ConvertEnDigitToFa.convert(String.valueOf(np_day.getValue()));
            String day2 = (String.format("%s", day1.length() < 2 ? "۰" + day1 : day1));

            String date = year1+"/"+month2+"/"+day2;

//            String date = ConvertEnDigitToFa.convert(year+"/"+month+"/"+day) ;
            listener.onAcceptButtonClicked(date);
            dialog.dismiss();
        });

        img_close.setOnClickListener(v -> dialog.dismiss());
        dialog.show();

    }

}
