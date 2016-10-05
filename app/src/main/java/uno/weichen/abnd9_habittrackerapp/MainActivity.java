package uno.weichen.abnd9_habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uno.weichen.abnd9_habittrackerapp.data.RunContract;
import uno.weichen.abnd9_habittrackerapp.data.RunDbHelper;

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

        long newRowId = db.insert(RunContract.RunEntry.TABLE_NAME, null, values);
    }

    //Question: How to properly format this JAVA doc?? I want to put my sample usage code but you can see it is in a weird format.

    /**
     * Data reading
     *
     * Usage sample
     *
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
     */
    private Cursor read() {
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
        return cursor;
    }
}
