package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.support.DLAPosProfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible to handle profiles.
 *
 * @author fpoli
 */
public class ProfileManager {

    private final ConfigDatabase _db;

    /**
     * Constructor.
     *
     * @param context the application context.
     */
    public ProfileManager(Context context) {
        _db = ConfigDatabase.getInstance(context);
    }

    /**
     * Constructor used only for test purposes.
     */
    ProfileManager(ConfigDatabase configDatabase) {
        _db = configDatabase;
    }

    /**
     * Function to get all currently supported profilesIds.
     *
     * @return a list containing all profilesIds.
     */
    public synchronized List<String> getAllProfileIds() {
        return _db.profileDao().getAll();
    }

    /**
     * Function to obtain a specific profile, knowing its Id.
     *
     * @param profileId the id of the desired profile.
     * @return the profile associated with the requested Id.
     * @throws APosException when it is impossible to parse the profile.
     */
    public synchronized DLAPosProfile getConfigurationForProfileId(String profileId) throws APosException {
        Profile tempResult = _db.profileDao().getProfile(profileId);
        try {
            DLAPosProfile result = new DLAPosProfile();
            result.load(tempResult.profile);
            return result;
        } catch (Exception e) {
            throw new APosException("Can not parse profile " + profileId + ".", e);
        }
    }

    /**
     * Function to remove all known profiles.
     */
    public synchronized void deleteAllProfiles() {
        _db.profileDao().removeAll();
    }

    /**
     * Function to check if a profile file exists in assets folder.
     *
     * @param context  the application context.
     * @param filePath the path of the file to test.
     * @return true if the file exists, false otherwise.
     * @throws APosException if can not access assets.
     */
    static public Boolean profileFileExists(Context context, String filePath) throws APosException {
        try {
            return Arrays.asList(context.getAssets().list("")).contains(filePath);
        } catch (IOException e) {
            throw new APosException("Can not access assets", e);
        }
    }

    /**
     * Function to pre-load a set of profiles from an assetFile.
     *
     * @param context  the application context.
     * @param filePath the path of the asset's file to load.
     */
    public synchronized void loadFromAssets(Context context, String filePath) throws APosException {
        _db.profileDao().insertAllUpdateMode(parseAssetsFile(context, filePath).toProfileList());
    }

    /**
     * Function to parse an existent JSon profile configuration file from assets.
     *
     * @param context  the application context.
     * @param filePath the path of the file to load.
     * @return the content of the json file.
     * @throws APosException when the file does not exists or does not contain a json.
     */
    static ProfileJsonHelper parseAssetsFile(Context context, String filePath) throws APosException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(filePath), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            return ProfileJsonHelper.load(reader);
        } catch (IOException e) {
            throw new APosException("Can not open the assets file " + filePath, e);
        }
    }
}
