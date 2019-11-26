package coursework.luyu.no217.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    private static String createEvent = "create table event(\n" +
            "id integer primary Key autoincrement,\n" +
            "title varchar(200) not null,\n" +
            "time varchar(50) not null,\n" +
            "content varchar(225) not null,\n" +
            "type varchar(50) not null,\n" +
            "position varchar(10) not null\n"+

            ");";


    public Helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL(createEvent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
