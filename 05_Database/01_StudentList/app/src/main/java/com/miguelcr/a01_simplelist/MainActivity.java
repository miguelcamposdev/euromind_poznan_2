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

import com.miguelcr.a01_simplelist.database.DaoMaster;
import com.miguelcr.a01_simplelist.database.DaoSession;
import com.miguelcr.a01_simplelist.database.Note;
import com.miguelcr.a01_simplelist.database.NoteDao;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    List<String> students = new ArrayList<>();
    ArrayAdapter<String> adapterStudents;
    EditText newStudentName;
    List<Note> notes;
    DaoSession daoSession;
    NoteDao noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database connection
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-poznan-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        // ******************************

        newStudentName = (EditText) findViewById(R.id.new_student_name);

        // 1. ListView component to draw Students
        lista = (ListView) findViewById(R.id.list_view_students);

        // 2. List of students
        // Get notes from Database
        noteManager = daoSession.getNoteDao();
        // SELECT * FROM Note
        notes = noteManager.loadAll();

        String[] notesArray = new String[notes.size()];

        // We shoudl transform the List<Note> to String[]
        for(int i=0; i<notes.size(); i++) {
            notesArray[i] = notes.get(i).getTitle();
        }

        // 3. Define an Adapter to draw the String[] students

        adapterStudents = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                notesArray
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
        String titleNote = newStudentName.getText().toString();

        // A new note object
        Note newNote = new Note();
        newNote.setTitle(titleNote);
        newNote.setUnits(1);

        // Insert the new note in the database
        // INSERT INTO Note ('title','units') VALUES (titleNote,1)
        noteManager.insert(newNote);

        // Add the new note to the String[]
        notes = noteManager.loadAll();

        String[] notesArray = new String[notes.size()];

        // We should transform the List<Note> to String[]
        for(int i=0; i<notes.size(); i++) {
            notesArray[i] = notes.get(i).getTitle();
        }

        adapterStudents.notifyDataSetChanged();
        newStudentName.setText("");
    }
}
