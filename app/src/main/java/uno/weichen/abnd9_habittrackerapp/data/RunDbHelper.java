package uno.weichen.abnd9_habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * RunHabit DB helper that to create/upgrade the SQL database.
 */
public class RunDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "habit.db";

    public RunDbHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habit table
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + RunContract.RunEntry.TABLE_NAME + " ("
            + RunContract.RunEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RunContract.RunEntry.COLUMN_RUN_DATE + " TEXT NOT NULL, "
            + RunContract.RunEntry.COLUMN_RUN_DISTANCE + " INTEGER NOT NULL DEFAULT 0, "
            + RunContract.RunEntry.COLUMN_RUN_ELAPSED_TIME + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}