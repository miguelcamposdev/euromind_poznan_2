package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDaoDatabaseGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.miguelcr.a01_simplelist.database");

        studentListSchema(schema);

        try {
            new DaoGenerator().generateAll(schema, "../01_StudentList/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void studentListSchema(Schema schema) {

        // Create the Entity Notes
        Entity note = schema.addEntity("Note");
        note.addIdProperty().autoincrement().primaryKey();
        note.addStringProperty("title");
        note.addIntProperty("units");
    }
}
