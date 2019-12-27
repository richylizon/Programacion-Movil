package com.loopwiki.loginregisterwithsqlite.citas_medicas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CitaMedicaDbHelp extends SQLiteOpenHelper {
    private static final String DB_NAME="citamedica.db";
    private static final int DB_VERSION=1;

    public CitaMedicaDbHelp(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_CITAMEDICA_TABLE="CREATE TABLE "+
                CitaMedicaContract.CitaMedicaEntry.TABLE_NAME + " ("+
                CitaMedicaContract.CitaMedicaEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CitaMedicaContract.CitaMedicaEntry.COLUMN_DOCTOR + " TEXT NOT NULL, "+
                CitaMedicaContract.CitaMedicaEntry.COLUMN_CLINICA + " TEXT NOT NULL, "+
                CitaMedicaContract.CitaMedicaEntry.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";
        db.execSQL(CREATE_CITAMEDICA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ CitaMedicaContract.CitaMedicaEntry.TABLE_NAME);
        onCreate(db);
    }
}
