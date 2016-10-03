package uno.weichen.abnd9_habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RunDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init the RunHabbit Db Helper
        mDbHelper = new RunDbHelper(this);

    }

    /**
     * Run Contract class define the database schema
     */
    public final class RunContract {

        public abstract class RunEntry implements BaseColumns {
            public static final String TABLE_NAME = "habit";

            /**
             * date is the string to record when the running event occurs. distance is the running
             * distance in meters. elapsed time is the running time in seconds.
             */
            public static final String _ID = BaseColumns._ID;
            public static final String COLUMN_RUN_DATE = "date";
            public static final String COLUMN_RUN_DISTANCE = "distance";
            public static final String COLUMN_RUN_ELAPSED_TIME = "elapsedTime";
        }

    }

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

    /**
     * Data insertion
     */
    private void insert() {
        String date = "Oct/2/2016";
        int distance = 5000;  //5000 meter
        int elapsedTime = 1800;   //1800 sec

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(RunContract.RunEntry.COLUMN_RUN_DATE, date);
        values.put(RunContract.RunEntry.COLUMN_RUN_DISTANCE, distance);
        values.put(RunContract.RunEntry.COLUMN_RUN_ELAPSED_TIME, elapsedTime);

    }

    /**
     * Data reading
     */
    private void read() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        String[] projection = {
            RunContract.RunEntry._ID,
            RunContract.RunEntry.COLUMN_RUN_DATE,
            RunContract.RunEntry.COLUMN_RUN_DISTANCE,
            RunContract.RunEntry.COLUMN_RUN_ELAPSED_TIME
        };

        Cursor cursor = db.query(
            RunContract.RunEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        );

        //We will read the data but we won't try to display in any UI component.
        try {
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(cursor.getColumnIndex(RunContract.RunEntry._ID));
                String currentName = cursor.getString(cursor.getColumnIndex(RunContract.RunEntry.COLUMN_RUN_DATE));
                int currentDistance = cursor.getInt(cursor.getColumnIndex(RunContract.RunEntry.COLUMN_RUN_DISTANCE));
                int currentElapsedTime = cursor.getInt(cursor.getColumnIndex(RunContract.RunEntry.COLUMN_RUN_ELAPSED_TIME));
            }
        } finally {
            cursor.close();
        }
    }
}
