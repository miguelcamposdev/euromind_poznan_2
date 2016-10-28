package com.miguelcr.a01_duckhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcr.a01_duckhunt.database.DaoSession;
import com.miguelcr.a01_duckhunt.database.DatabaseConnection;
import com.miguelcr.a01_duckhunt.database.User;
import com.miguelcr.a01_duckhunt.database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class RankingUserActivity extends AppCompatActivity {
    private List<String> ranking;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_user);

        // 1. Get the ListView component
        lista = (ListView) findViewById(R.id.list_view_ranking);

        // 2. Create a list of elements to draw
        DaoSession connection = DatabaseConnection.getConnection(this);
        UserDao userManager = connection.getUserDao();

        //List<User> users = userManager.loadAll();
        List<User> users = userManager.queryBuilder()
                .orderDesc(UserDao.Properties.Score)
                .list();

        ranking = new ArrayList<>();

        for(int i=0; i<users.size(); i++) {
            User current = users.get(i);
            ranking.add(current.getNick()+" - "+current.getScore());
        }

        // 3. Create the default Android Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ranking
        );

        // 4. Connect ListView and Adapter
        lista.setAdapter(adapter);
    }
}
