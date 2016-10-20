package com.miguelcr.a01_simplelist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    List<String> students = new ArrayList<>();
    ArrayAdapter<String> adapterStudents;
    EditText newStudentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newStudentName = (EditText) findViewById(R.id.new_student_name);

        // 1. ListView component to draw Students
        lista = (ListView) findViewById(R.id.list_view_students);

        // 2. List of students
        students.add("Eryk");
        students.add("Adrian");
        students.add("Jan");
        students.add("Michal");
        students.add("Maciej");
        students.add("Dawid");
        students.add("Zosia"); // Sofia
        students.add("Pawel");
        students.add("Kacper");
        students.add("Michal");
        students.add("Weronika");
        students.add("Patryk");
        students.add("Pawel");
        students.add("Maia");
        students.add("Kuba");
        students.add("Michal");
        students.add("Eryk");

        // 3. Define an Adapter to draw the String[] students

        adapterStudents = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                students
        );

        // 4. Connect the ListView with the Adapter
        lista.setAdapter(adapterStudents);

        // Manage the event click on list items
        lista.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String studentSelected = students.get(position);
        Toast.makeText(this, "Student: "+studentSelected, Toast.LENGTH_SHORT).show();

        view.animate().rotationXBy(360).setDuration(2000).start();

        // We must to transform the view object to TextView
        TextView text = (TextView) view;

        // And now we can change de color
        text.setTextColor(Color.parseColor("#FF0000"));

    }

    public void addStudent(View view) {
        String studentToAdd = newStudentName.getText().toString();
        students.add(studentToAdd);
        adapterStudents.notifyDataSetChanged();
        newStudentName.setText("");
    }
}
