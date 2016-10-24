package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDaoDatabaseGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.miguelcr.a02_completenotelist.database");

        studentListSchema(schema);

        try {
            new DaoGenerator().generateAll(schema, "../02_CompleteNoteList/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void studentListSchema(Schema schema) {

        // Create the Entity Notes
        Entity user = schema.addEntity("User");

        // ID
        user.addIdProperty().autoincrement().primaryKey();

        // Name
        user.addStringProperty("name");

        // Email
        user.addStringProperty("email");

        // Password
        user.addStringProperty("password");
    }
}
