package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EciCSVHelper {

    final static String COMMENT_LINE_HEADER = "//";
    final static String SEPARATOR = ",";

    static List<Eci> load(BufferedReader bufferedReader) throws APosException {
        try {
            ArrayList<Eci> result = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith(COMMENT_LINE_HEADER)) {
                    String[] tokens = line.split(SEPARATOR, -1);
                    if (tokens.length < 2)
                        throw new APosException("Malformed line (only " + tokens.length + " fields) in ECI file:\n" + line);
                    try {
                        result.add(new Eci(Integer.parseInt(tokens[0]), tokens[1]));
                    } catch (NumberFormatException exc) {
                        throw new APosException("Malformed line (the id is not an int) in ECI file:\n" + line, exc);
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new APosException("Reading the Eci config.", e);
        }
    }
}
