package com.example.appartamentofacile.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, CardItem.class}, version = 1, exportSchema = false)
abstract class RoomDatabase extends androidx.room.RoomDatabase {
    abstract UserDAO userDAO();
    abstract CardItemDAO cardItemDAO();

    //Singleton instance
    private static volatile RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    //ExecutorService with a fixed thread pool that you will use to run database operations
    // asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * It'll create the database the first time it's accessed, using Room's database
     * @param context the context of the Application
     * @return the singleton
     */
    static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
