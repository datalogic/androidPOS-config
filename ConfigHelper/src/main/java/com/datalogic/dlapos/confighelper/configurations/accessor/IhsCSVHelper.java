package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class IhsCSVHelper {
    final static String COMMENT_LINE_HEADER = "//";
    final static String SEPARATOR = ",";
    final static String SECTION_SEPARATOR_HEADER = "-----";

    static List<Frame> load(BufferedReader reader) throws APosException {
        try {
            ArrayList<Frame> result = new ArrayList<>();
            String line;
            IhsHelper.FrameType section = IhsHelper.FrameType.UNKNOWN;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(COMMENT_LINE_HEADER)) {
                    if (line.startsWith(SECTION_SEPARATOR_HEADER)) {
                        section = getSection(line);
                    } else {
                        String[] tokens = line.split(SEPARATOR, -1);
                        if (tokens.length < 3)
                            throw new APosException("Malformed line (only " + tokens.length + " fields) in IHS file:\n" + line);
                        if (section == IhsHelper.FrameType.UNKNOWN)
                            throw new APosException("Malformed IHS file, section is not set.");
                        result.add(new Frame(tokens[0], tokens[1], tokens[2], section.name()));
                    }
                }
            }
            return result;
        } catch (IOException e) {
            throw new APosException("Reading the IHS config.", e);
        }
    }

    private static IhsHelper.FrameType getSection(String sectionLine) throws APosException {
        try {
            String sectionName = sectionLine.split(SECTION_SEPARATOR_HEADER)[1];
            switch (sectionName) {
                case "INFORMATION":
                    return IhsHelper.FrameType.INFORMATION;
                case "HEALTH":
                    return IhsHelper.FrameType.HEALTH;
                case "STATISTICS":
                    return IhsHelper.FrameType.STATISTICS;
                default:
                    throw new APosException("Unknown section in IHS file: " + sectionName);
            }
        } catch (NullPointerException e) {
            throw new APosException("Invalid section in IHS file: " + sectionLine);
        }
    }
}
