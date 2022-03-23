package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import com.datalogic.dlapos.commons.constant.ScannerConstants;
import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Helper for label ids.
 *
 * @author fpoli
 */
public class LabelHelper {

    private final static String LABEL_CSV_FILE = "labelIds.csv";
    private final static String noCode = "";

    private final ConfigDatabase _db;

    private DecodeType _decodeType = DecodeType.USCODE;

    /**
     * Supported decode types.
     */
    public enum DecodeType {
        /**
         * US code standard.
         */
        USCODE,
        /**
         * EU code standard.
         */
        EUCODE,
        /**
         * SCRS232 standard.
         */
        SCRS232,
        /**
         * IBM USB standard.
         */
        IBMUSBOEM
    }

    /**
     * Constructor.
     *
     * @param context the application context.
     */
    public LabelHelper(Context context) {
        _db = ConfigDatabase.getInstance(context);
    }

    //For test purposes only.
    LabelHelper(ConfigDatabase db) {
        this._db = db;
    }

    /**
     * Function to set the decode type.
     *
     * @param desiredDecodeType the decode type to set.
     */
    public void setDecodeType(DecodeType desiredDecodeType) {
        this._decodeType = desiredDecodeType;
    }

    /**
     * Function to get labels IDs starting from an identifier of the previously set DecodeType
     *
     * @param identifier to search for in labelIDs.
     * @return the label IDs correlated with the identifier passed as input.
     */
    public LabelIds getLabelsIds(byte[] identifier) {
        String id = idToString(identifier);
        LabelIds result = null;
        switch (_decodeType) {
            case USCODE:
                result = _db.labelDao().getByUSCode(id);
                break;
            case EUCODE:
                result = _db.labelDao().getByEUCode(id);
                break;
            case SCRS232:
                result = _db.labelDao().getBySCRS232(id);
                break;
            case IBMUSBOEM:
                result = _db.labelDao().getByIBMUSBOEM(id);
                break;
            default:
                break;
        }
        if (result == null)
            return new LabelIds("SCAN_SDT_UNKNOWN", ScannerConstants.SCAN_SDT_UNKNOWN,
                    "CI_LABEL_ID_UNKNOWN", "noCode", noCode, noCode, noCode, noCode, noCode);
        else
            return result;

    }

    /**
     * Function to initialize this helper.
     *
     * @param context the application context.
     * @throws APosException when an error is encountered.
     */
    public synchronized void initialize(Context context) throws APosException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(LABEL_CSV_FILE), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            _db.labelDao().upsert(LabelCSVHelper.load(reader));
        } catch (IOException e) {
            throw new APosException("Can not open the assets file " + LABEL_CSV_FILE, e);
        }
    }

    static String idToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (byte b : bytes) {
            buff.append(String.format("%02X", b));
        }
        return buff.toString();
    }

}
