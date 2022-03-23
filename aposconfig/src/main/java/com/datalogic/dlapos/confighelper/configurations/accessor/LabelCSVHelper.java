package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LabelCSVHelper {
    final static String COMMENT_LINE_HEADER = "//";
    final static String SEPARATOR = ",";

    static List<LabelIds> load(BufferedReader bufferedReader) throws APosException {
        try {
            ArrayList<LabelIds> result = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(COMMENT_LINE_HEADER)) {
                    String[] tokens = line.split(SEPARATOR,-1);
                    if (tokens.length < 9) {
                        throw new APosException("Malformed line (only " + tokens.length + " fields) in labelIds file:\n" + line);
                    }
                    try {
                        result.add(new LabelIds(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]));
                    } catch (NumberFormatException exc) {
                        throw new APosException("Malformed line (the id is not an int) in labelIds file:\n" + line, exc);
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new APosException("Reading the Label file.", e);
        }
    }
}
