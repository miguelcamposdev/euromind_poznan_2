package com.miguelcr.a01_duckhunt.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by miguelcampos on 24/10/16.
 */

public class DatabaseConnection {

    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoSession getConnection(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx,
                "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }
}
