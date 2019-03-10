package com.example.assignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Database version
    private static final int DATABASE_VERSION = 1;


    // Database name
    private static final String DATABASE_NAME = "ASAP";


    // Short term note attributes
    private static final String TABLE_SHORTNOTE = "ShortTermNote";

    private static final String COLUMN_SHORTNOTE_ID = "Id";
    private static final String COLUMN_SHORTNOTE_TITLE = "Title";
    private static final String COLUMN_SHORTNOTE_CONTENT = "Content";
    private static final String COLUMN_SHORTNOTE_DEADLINE = "DeadLine";
    private static final String COLUMN_SHORTNOTE_ISDEAD = "IsDead";
    private static final String COLUMN_SHORTNOTE_LONGNOTEID = "LongNoteId";

    // Long term note attributes
    private static final String TABLE_LONGNOTE = "LongTermNote";

    private static final String COLUMN_LONGNOTE_ID = "Id";
    private static final String COLUMN_LONGNOTE_TITLE = "Title";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Table create scripts
        String script = "CREATE TABLE " + TABLE_SHORTNOTE + "("
                + COLUMN_SHORTNOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_SHORTNOTE_TITLE + " TEXT NOT NULL,"
                + COLUMN_SHORTNOTE_CONTENT + " TEXT NOT NULL," + COLUMN_SHORTNOTE_DEADLINE + " TEXT NOT NULL,"
                + COLUMN_SHORTNOTE_ISDEAD + " INTEGER NOT NULL," + COLUMN_SHORTNOTE_LONGNOTEID + " INTEGER,"
                + " FOREIGN KEY (" + COLUMN_SHORTNOTE_LONGNOTEID + ") REFERENCES " + TABLE_LONGNOTE + "(" + COLUMN_LONGNOTE_ID + ")" + ")";

        String script2 = "CREATE TABLE " + TABLE_LONGNOTE + "("
                + COLUMN_LONGNOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_LONGNOTE_TITLE + " TEXT NOT NULL" + ")";
        // Run script to create tables
        db.execSQL(script);
        db.execSQL(script2);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Drop old tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHORTNOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LONGNOTE);

        // Create new one
        onCreate(db);
    }

    //add a short term note
    public long addShortTermNote(ShortTermNote note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SHORTNOTE_TITLE, note.getTitle());
        values.put(COLUMN_SHORTNOTE_CONTENT, note.getContent());
        values.put(COLUMN_SHORTNOTE_DEADLINE, note.getDeadline());
        values.put(COLUMN_SHORTNOTE_ISDEAD, note.getIsDeleted());
        values.put(COLUMN_SHORTNOTE_LONGNOTEID, note.getLongNoteId());

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_SHORTNOTE, null, values);

        return result;
    }

    //add a long term note
    public long addLongTermNote(LongTermNote note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LONGNOTE_TITLE, note.getTitle());

        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE_SHORTNOTE, null, values);

        return result;
    }

    //find a short term note
    public ShortTermNote findShortTermNote(String noteName) {
        //TODO
        ShortTermNote note = new ShortTermNote();
        String query = "Select * From " + TABLE_SHORTNOTE
                + " Where " + COLUMN_SHORTNOTE_TITLE + " = '" + noteName + "'";

        return null;
    }
}
