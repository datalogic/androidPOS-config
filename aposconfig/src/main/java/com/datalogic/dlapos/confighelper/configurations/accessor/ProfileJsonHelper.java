package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.support.ProfileConfiguration;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

class ProfileJsonHelper {
    private String version;
    private ProfileConfiguration[] aPosEntries;

    public String getVersion() {
        return version;
    }

    public ProfileConfiguration[] getAPosEntries() {
        return aPosEntries;
    }

    public List<Profile> toProfileList() {
        ArrayList<Profile> result = new ArrayList<>();
        for (ProfileConfiguration entry : aPosEntries) {
            result.add(new Profile(entry.getProfileID(), entry.toString()));
        }
        return result;
    }

    static ProfileJsonHelper load(BufferedReader bufferedReader) throws APosException {
        try {
            return new Gson().fromJson(bufferedReader, ProfileJsonHelper.class);
        } catch (Exception e) {
            throw new APosException("Malformed assets file", e);
        }
    }

}
