package com.example.temp_converter_task;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton btnSelected;
    EditText value;
    Button convert;
    TextView result1;
    TextView result2;
    TextView urlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.scales);
        value = findViewById(R.id.value);
        convert = findViewById(R.id.convert);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = 0;
                double val = 0;

                try {
                    if (value.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter a value!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    val = Double.parseDouble(value.getText().toString());
                } catch (Exception e) {
                    val = 0;
                    Toast.makeText(MainActivity.this, "Please enter numerical value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    radioID= radioGroup.getCheckedRadioButtonId();
                    btnSelected = findViewById(radioID);
                    closeKeyboard();

                    if (btnSelected.getText().toString().toLowerCase().equals("celcius")) {
                        forCelcius(Double.parseDouble(value.getText().toString()));
                        return;
                    } else if (btnSelected.getText().toString().toLowerCase().equals("kelvin")) {
                        forKelvin(Double.parseDouble(value.getText().toString()));
                        return;
                    } else {
                        forFahrenheit(Double.parseDouble(value.getText().toString()));
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Please select a scale!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        urlBtn = findViewById(R.id.dev);
        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.linkedin.com/in/prasantcp/";
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void forCelcius(double c) {
        double f = (double)((c * 9)/5) + 32;
        double k = c + 273.15;

        result1.setText(f +  "° F");
        result2.setText(k + "° K");
    }

    public void forKelvin(double k) {
        double c = k - 273.15;
        double f = (double)((c * 9)/5) + 32;


        result1.setText(c +  "° C");
        result2.setText(f + "° F");
    }

    public void forFahrenheit(double f) {
        double c = ((f - 32) * 5) / 9;
        double k = c + 273.15;

        result1.setText(c +  "° C");
        result2.setText(k + "° K");
    }
}