package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface LabelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<LabelIds> labelIds);

    @Query("SELECT * FROM LabelIds WHERE usCode IS :USCode")
    LabelIds getByUSCode(String USCode);

    @Query("SELECT * FROM LabelIds WHERE euCode IS :EUCode")
    LabelIds getByEUCode(String EUCode);

    @Query("SELECT * FROM LabelIds WHERE ibmUsbOem IS :IBMUSBOEM")
    LabelIds getByIBMUSBOEM(String IBMUSBOEM);

    @Query("SELECT * FROM LabelIds WHERE scrs232 IS :SCRS232")
    LabelIds getBySCRS232(String SCRS232);

    @Query("SELECT * FROM LabelIds WHERE outputPrefix IS :outputPrefix")
    LabelIds getByOutputPrefix(String outputPrefix);

    @Query("SELECT * FROM LabelIds WHERE outputSuffix IS :outputSuffix")
    LabelIds getByOutputSuffix(String outputSuffix);

}
