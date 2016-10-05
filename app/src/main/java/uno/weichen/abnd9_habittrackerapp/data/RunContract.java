package uno.weichen.abnd9_habittrackerapp.data;

import android.provider.BaseColumns;

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