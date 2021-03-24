package com.servicenow.sample.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.servicenow.sample.repository.database.models.Joke;

import static com.servicenow.sample.utils.IDatabaseConstants.DATABASE_NAME;
import static com.servicenow.sample.utils.IDatabaseConstants.DATABASE_VERSION;

@Database(entities = {
        Joke.class
}, version = DATABASE_VERSION, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase implements IDatabase {

    private static IDatabase mInstance;

    public static IDatabase getInstance(Context context) {
        if (mInstance == null)
            mInstance = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME)
                    .build();
        return mInstance;

    }

}
