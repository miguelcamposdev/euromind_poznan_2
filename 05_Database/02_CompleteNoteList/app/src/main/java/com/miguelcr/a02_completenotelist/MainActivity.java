package com.miguelcr.a02_completenotelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSignUp(View view) {
        // Launch the RegisterActivity
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
