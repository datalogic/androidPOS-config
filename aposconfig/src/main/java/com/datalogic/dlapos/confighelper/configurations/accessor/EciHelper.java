package com.datalogic.dlapos.confighelper.configurations.accessor;


import android.content.Context;

import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Helper to access ECI encoding data.
 *
 * @author fpoli
 */
public class EciHelper {

    private final static String ECIENCODING_CSV_FILE = "ECIEncoding.csv";

    private final ConfigDatabase _db;

    /**
     * Constructor.
     *
     * @param context the application context.
     */
    public EciHelper(Context context) {
        _db = ConfigDatabase.getInstance(context);
    }

    EciHelper(ConfigDatabase db) {
        _db = db;
    }

    /**
     * Function to parse an ECI code, obtaining its JavaCode as a String.
     *
     * @param eciCode to parse.
     * @return the correspondent JavaCode.
     */
    public String getJavaCode(int eciCode) {
        return _db.eciDao().getJavaCode(eciCode);
    }

    /**
     * Function to initialize this helper.
     *
     * @param context the application context.
     * @throws APosException when an error is encountered.
     */
    public synchronized void initialize(Context context) throws APosException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(ECIENCODING_CSV_FILE), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            _db.eciDao().upsertEciCodes(EciCSVHelper.load(reader));
        } catch (IOException e) {
            throw new APosException("Can not open the assets file " + ECIENCODING_CSV_FILE, e);
        }

    }

}
