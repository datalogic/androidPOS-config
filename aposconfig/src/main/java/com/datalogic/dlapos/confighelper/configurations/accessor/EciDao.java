package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface EciDao {

    @Query("SELECT javaCode FROM Eci WHERE eciCode IS :eciCode")
    String getJavaCode(int eciCode);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertEciCodes(List<Eci> toAdd);
}
