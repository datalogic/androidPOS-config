package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface ProfileDao {

    @Query("SELECT profileId FROM Profile")
    List<String> getAll();

    @Query("SELECT * FROM Profile WHERE profileId IS :profileID ")
    Profile getProfile(String profileID);

    @Query("DELETE FROM Profile WHERE profileId IS :profileID")
    void removeProfile(String profileID);

    @Query("DELETE FROM Profile")
    void removeAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile profile);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllDropMode(List<Profile> profiles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUpdateMode(List<Profile> profiles);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProfileIfNotPresent(Profile profile);
}
