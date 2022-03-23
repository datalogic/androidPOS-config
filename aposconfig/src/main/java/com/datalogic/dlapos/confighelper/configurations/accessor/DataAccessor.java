package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.support.DLAPosProfile;

import java.util.List;

interface DataAccessor {

    List<String> getAllProfiles();

    DLAPosProfile getProfile(String profileId) throws APosException;

    void removeAll();

    void insertAll(List<Profile> dataToInsert, Boolean updateMode);
}
