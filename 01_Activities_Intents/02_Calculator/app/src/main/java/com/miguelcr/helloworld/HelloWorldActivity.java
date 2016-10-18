package com.miguelcr.helloworld;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorldActivity extends AppCompatActivity {
    TextView screen;
    Button btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);


        screen = (TextView)findViewById(R.id.textview_screen);
        btn7 = (Button) findViewById(R.id.button_7);

        // Change screen text
        screen.setText("0");

    }


    public void writeNumber(View view) {
        Button buttonClicked = (Button)view;
        int idButtonClicked = buttonClicked.getId();

        if(idButtonClicked==R.id.button_7) {
            screen.setText("7");
        } else if(idButtonClicked==R.id.button_8) {
            screen.setText("8");
        }
    }
}
