package ru.anstag.app.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String DATE_FIELD = "date";

    public DatabaseManager(Context context){
        super(context, "notes_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db", "onCreate");
        String sql = "CREATE TABLE " + TABLE_NAME
                + " (" + ID_FIELD + " INTEGER, "
                + NAME_FIELD + " TEXT,"
                + DESCRIPTION_FIELD + " TEXT,"
                + DATE_FIELD + " TEXT,"
                + " PRIMARY KEY (" + ID_FIELD + "));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("db", "onUpdate");
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        // пересоздание таблицы
        onCreate(db);
    }

    public Notes addNotes(Notes notes){

        Log.d("db", "addNote");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_FIELD, notes.getName());
        values.put(DESCRIPTION_FIELD, notes.getDescription());
        values.put(DATE_FIELD, notes.getDate());

        long id = db.insert(TABLE_NAME, null, values);
        notes.setId(id);
        db.close();
        return notes;
    }

    Notes getNotes(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        ID_FIELD, NAME_FIELD, DESCRIPTION_FIELD,
                       DATE_FIELD}, ID_FIELD + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Notes notes = new Notes(
                    cursor.getString(1),
                    cursor.getString(2));
            notes.setId(cursor.getLong(0));
            return notes;
        }

        return null;
    }

    public Cursor getNotesCursor() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    public void deleteNotes(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_FIELD + " = ? ", new String[] { String.valueOf(id) });
        db.close();
    }
}

