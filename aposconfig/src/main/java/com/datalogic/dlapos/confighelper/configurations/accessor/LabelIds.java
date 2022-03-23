package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class containing identification values for a specific label.
 *
 * @author fpoli
 */
@Entity
public class LabelIds {

    @PrimaryKey
    @NonNull
    final String tagName;
    final String uposName;
    final int uposId;
    final String usCode;
    final String euCode;
    final String ibmUsbOem;
    final String scrs232;
    final String outputPrefix;
    final String outputSuffix;

    /**
     * Constructor.
     *
     * @param uposName     the UPOS name of the label.
     * @param uposId       the UPOS identifier of the label
     * @param tagName      the tag of the label (can not be null).
     * @param usCode       the US code identifier of the label.
     * @param euCode       the EU code identifier of the label.
     * @param ibmUsbOem    the IBMOEM identifier of the label.
     * @param scrs232      the SRCS232 identifier of the label.
     * @param outputPrefix the output prefix of the label.
     * @param outputSuffix the output suffix of the label.
     */
    public LabelIds(String uposName, int uposId, @NonNull String tagName, String usCode, String euCode, String ibmUsbOem, String scrs232, String outputPrefix, String outputSuffix) {
        this.uposName = uposName;
        this.uposId = uposId;
        this.tagName = tagName;
        this.usCode = usCode;
        this.euCode = euCode;
        this.ibmUsbOem = ibmUsbOem;
        this.scrs232 = scrs232;
        this.outputPrefix = outputPrefix;
        this.outputSuffix = outputSuffix;
    }

    /**
     * Getter for the UPOS name of the label.
     *
     * @return the UPOS name of the label.
     */
    public String getUposName() {
        return uposName;
    }

    /**
     * Getter for the UPOS identifier of the label.
     *
     * @return the UPOS identifier of the label.
     */
    public int getUposId() {
        return uposId;
    }

    /**
     * Getter for the tag name of the label.
     *
     * @return the tag name of the label.
     */
    @NonNull
    public String getTagName() {
        return tagName;
    }

    /**
     * Getter for the US code identifier of the label.
     *
     * @return the US code identifier of the label, a void string if it does not applies.
     */
    public String getUsCode() {
        return usCode;
    }

    /**
     * Getter for the EU code identifier of the label.
     *
     * @return the EU code identifier of the label, a void string if it does not applies.
     */
    public String getEuCode() {
        return euCode;
    }

    /**
     * Getter for the IBMOEM identifier of the label.
     *
     * @return the IBMOEM identifier of the label, a void string if it does not applies.
     */
    public String getIbmUsbOem() {
        return ibmUsbOem;
    }

    /**
     * Getter for the SCRS232 identifier of the label.
     *
     * @return the SCRS232 identifier of the label, a void string if it does not applies.
     */
    public String getScrs232() {
        return scrs232;
    }

    /**
     * Getter for the output prefix of the label.
     *
     * @return the output prefix of the label, a void string if it does not applies.
     */
    public String getOutputPrefix() {
        return outputPrefix;
    }

    /**
     * Getter for the output suffix of the label.
     *
     * @return the output suffix of the label, a void string if it does not applies.
     */
    public String getOutputSuffix() {
        return outputSuffix;
    }

}
