package com.example.androivalidate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private  static  final  String TAG = "MainActivity";
    private EditText displayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button btnSubmit;
    private Spinner properties;
    private EditText monthlyRentPrice;
    private Spinner furnitureType;
    private Spinner bedRoomType;
    private EditText nameReporter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayDate = (EditText) findViewById(R.id.dateTimeEdit);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrValidate = {};
                List<String> listValidate = new ArrayList<>(Arrays.asList((arrValidate)));

                String[] listProperties = {"Flat", "House", "Bungalow"};
                String[] listFurnished = {"Furnished", "Unfurnished", "PartFurnished"};

                List<String> listPropertyType = new ArrayList<>(Arrays.asList(listProperties));
                List<String> listFurnitureTypes = new ArrayList<>(Arrays.asList(listFurnished));

                properties = findViewById(R.id.property_type);
                String valPropertyType = properties.getSelectedItem().toString();

                bedRoomType = findViewById(R.id.bed_room);
                String valBedrooms = bedRoomType.getSelectedItem().toString();

                displayDate = findViewById(R.id.dateTimeEdit);
                String valDateTimeAdding = displayDate.getText().toString();

                monthlyRentPrice = findViewById(R.id.monthly_rent_price);
                String valMonthlyRentPrice = monthlyRentPrice.getText().toString();

                furnitureType = findViewById(R.id.furnishture_type);
                String valFurnitureTypes = furnitureType.getSelectedItem().toString();




                nameReporter = findViewById(R.id.reporter);
                String valNameReporter = nameReporter.getText().toString();

                if (!(listPropertyType.contains(valPropertyType))) {
                    listValidate.add("Property Type");
                }

                if (!(isNumber(valBedrooms))) {
                    listValidate.add("Bed rooms");
                }

                if (!(isValidateDate(valDateTimeAdding))) {
                    listValidate.add("Date Time");
                }

                if (!(isNumber(valMonthlyRentPrice))) {
                    listValidate.add("Monthly Rent Price");
                }

                if (!(listFurnitureTypes.contains(valFurnitureTypes))) {
                    listValidate.add("Furniture types");
                }

                if (isEmptyOrWhiteSpace(valNameReporter)) {
                    listValidate.add("Name Reporter");
                }

                if (listValidate.size() > 0) {
                    Toast makeToast = Toast.makeText(MainActivity.this, errMessage(listValidate), Toast.LENGTH_LONG);
                    makeToast.show();
                } else {
                    Toast makeToast = Toast.makeText(MainActivity.this, "You have successfully created the form!", Toast.LENGTH_LONG);
                    makeToast.show();
                }
            }
        });
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                            android.R.style.Theme_Holo_Dialog_MinWidth,
                            onDateSetListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };
    }

    public final static String errMessage(List isValidate) {
        String err = "";
        for (int i = 0; i < isValidate.size(); i++) {
            err += (isValidate.get(i) + ", ");
        }

        return "You need to enter + " + err  + "\n";
    }

    public final static boolean isValidateDate(String el)
    {
        return Pattern.compile("^\\d{4}[\\/.]\\d{1,2}[\\/.]\\d{1,2}$").matcher(el).matches();
    }

    public final static boolean isEmptyOrWhiteSpace(String el)
    {
        return Pattern.compile("^\\s*$").matcher(el).matches();
    }

    public final static boolean isNumber(String el)
    {
        return Pattern.compile("^\\d+$").matcher(el).matches();
    }
}