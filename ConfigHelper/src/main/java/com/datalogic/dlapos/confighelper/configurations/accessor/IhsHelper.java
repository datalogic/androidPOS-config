package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.core.util.Pair;

import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Helper to parse information's, health's and statistic's data.
 *
 * @author fpoli
 */
public class IhsHelper {
    private static final String IHS_CSV_FILE = "IHSParser.csv";
    private final ConfigDatabase _db;

    /**
     * Supported frame's types
     */
    public enum FrameType {
        /**
         * Information frame.
         */
        INFORMATION,
        /**
         * Health frame.
         */
        HEALTH,
        /**
         * Statistics frame.
         */
        STATISTICS,
        /**
         * Unknown frame.
         */
        UNKNOWN
    }

    /**
     * Constructor.
     *
     * @param context the application context.
     */
    public IhsHelper(Context context) {
        _db = ConfigDatabase.getInstance(context);
    }

    //For test purposes only.
    IhsHelper(ConfigDatabase db) {
        _db = db;
    }

    /**
     * Function to obtain the field name associated with the Frame id.
     *
     * @param id        the frame id of the field name to get.
     * @param frameType the type of data useful to the parser.
     * @return the field name requested or an empty string if it does not exists.
     */
    public String getFieldName(String id, FrameType frameType) {
        Frame f = _db.FrameDao().getFrame(id, frameType.name());
        return f != null ? f.getFieldName() : "";
    }

    /**
     * Function to parse a frame obtaining a couple key value.
     *
     * @param frameId   the id of the frame to parse.
     * @param contents  the content to parse
     * @param frameType the type of the frame
     * @return a pair key value.
     */
    public Pair<String, String> parse(String frameId, String contents, FrameType frameType) {
        return _db.FrameDao().parse(frameId, contents, frameType);
    }

    /**
     * Function to obtain all names for a specific frame type.
     *
     * @param type the desired frame type.
     * @return a list containing all field's name for the required type.
     */
    public List<String> getAllFieldNamesForType(FrameType type) {
        return _db.FrameDao().getAllNamesForFrameType(type.name());
    }

    /**
     * Function to initialize this helper.
     *
     * @param context the application context.
     * @throws APosException when an error is encountered.
     */
    public synchronized void initialize(Context context) throws APosException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(IHS_CSV_FILE), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            _db.FrameDao().insert(IhsCSVHelper.load(reader));
        } catch (IOException e) {
            throw new APosException("Can not open the assets file " + IHS_CSV_FILE, e);
        }

    }
}
