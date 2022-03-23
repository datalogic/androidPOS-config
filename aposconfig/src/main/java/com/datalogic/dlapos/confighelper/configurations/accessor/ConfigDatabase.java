package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Profile.class, Eci.class, LabelIds.class, Frame.class}, version = 1, exportSchema = true)
abstract class ConfigDatabase extends RoomDatabase {

    public final static String DB_NAME = "configurations";
    private static ConfigDatabase _instance;

    protected ConfigDatabase() {
    }

    public static synchronized ConfigDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = Room.databaseBuilder(context, ConfigDatabase.class, DB_NAME).build();
        }
        return _instance;
    }

    public abstract ProfileDao profileDao();

    public abstract EciDao eciDao();

    public abstract LabelDao labelDao();

    public abstract FrameDao FrameDao();
}
