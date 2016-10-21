package com.miguelcr.a02_customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<AndroidVersions> androidVersionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Get the ListView
        lista = (ListView) findViewById(R.id.list_view);

        // 2. Load the List of data that we want to draw in ListView
        androidVersionsList = new ArrayList<>();
        androidVersionsList.add(new AndroidVersions("Kitkat",R.drawable.ic_chocolate));
        androidVersionsList.add(new AndroidVersions("Cupcake",R.drawable.ic_cupcake));
        androidVersionsList.add(new AndroidVersions("Doughnut",R.drawable.ic_doughnut));
        androidVersionsList.add(new AndroidVersions("Ice cream",R.drawable.ic_ice_cream));
        androidVersionsList.add(new AndroidVersions("Lollipop",R.drawable.ic_lollipop));

        // 3. We must to create our custom adapter!!!!!!!!!!!

        AndroidVersionAdapter adapter = new AndroidVersionAdapter(
                this,
                R.layout.android_version_item,
                androidVersionsList
        );


        // 4. Connect the ListView (lista) with Adapter
        lista.setAdapter(adapter);
    }
}
