package com.datalogic.dlapos.confighelper.configurations.accessor;


import androidx.core.util.Pair;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
abstract class FrameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(List<Frame> frames);

    @Query("SELECT * FROM frame WHERE id IS :id AND type IS :type")
    abstract Frame getFrame(String id, String type);

    @Query("SELECT DISTINCT fieldName FROM frame WHERE type IS :type")
    abstract List<String> getAllNamesForFrameType(String type);

    @Transaction
    public Pair<String, String> parse(String id, String contents, IhsHelper.FrameType frameType) {
        Frame frame = getFrame(id, frameType.name());
        if (frame == null)
            return null;
        if (frame.getAdditionalData().equals("")) {
            return new Pair<>(frame.getFieldName(), contents);
        } else {
            Frame newFrame = getFrame(id + contents, frameType.name());
            if (newFrame == null)
                return null;
            return new Pair<>(newFrame.getFieldName(), newFrame.getAdditionalData());
        }
    }
}
