package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGreenDaoDatabaseGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.miguelcr.a01_duckhunt.database");

        duckHuntSchema(schema);

        try {
            new DaoGenerator().generateAll(schema, "../01_DuckHunt/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void duckHuntSchema(Schema schema) {

        // Create the Entity Notes
        Entity user = schema.addEntity("User");

        // ID
        user.addIdProperty().autoincrement().primaryKey();

        // Nick
        user.addStringProperty("nick");

        // Score
        user.addIntProperty("score");

    }
}
