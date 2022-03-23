package com.datalogic.dlapos.confighelper;

import android.content.Context;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.accessor.EciHelper;
import com.datalogic.dlapos.confighelper.configurations.accessor.IhsHelper;
import com.datalogic.dlapos.confighelper.configurations.accessor.LabelHelper;
import com.datalogic.dlapos.confighelper.configurations.accessor.ProfileManager;
import com.datalogic.dlapos.confighelper.configurations.support.Creation;

/**
 * Class representing the entry point of the library.
 * It allows to create a Service and to obtains configurations.
 *
 * @author fpoli
 */
public class DLAPosConfigHelper {
    private static DLAPosConfigHelper _instance;

    private final ProfileManager _profileManager;
    private final EciHelper _eciHelper;
    private final LabelHelper _labelHelper;
    private final IhsHelper _ihsHelper;
    private boolean _initialized = false;

    public static final String DEFAULT_PROFILE_FILE = "apos.json";

    public static DLAPosConfigHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new DLAPosConfigHelper(context);
        }
        return _instance;
    }

    /**
     * Constructor
     *
     * @param context the application context.
     */
    DLAPosConfigHelper(Context context) {
        _profileManager = new ProfileManager(context);
        _eciHelper = new EciHelper(context);
        _labelHelper = new LabelHelper(context);
        _ihsHelper = new IhsHelper(context);
    }

    /**
     * Function to initialize the profile manager using the default profile file.
     *
     * @param context the application context.
     * @throws APosException if the initialization fails.
     */
    public synchronized void initialize(Context context) throws APosException {
        if (ProfileManager.profileFileExists(context, DEFAULT_PROFILE_FILE))
            _profileManager.loadFromAssets(context, DEFAULT_PROFILE_FILE);
        _ihsHelper.initialize(context);
        _labelHelper.initialize(context);
        _eciHelper.initialize(context);
        _initialized = true;
    }

    public boolean isInitialized() {
        return _initialized;
    }

    /**
     * Function to get a ProfileManager to access profiles and properties.
     *
     * @return a ProfileManager containing all known profiles.
     */
    public ProfileManager getProfileManager() {
        return _profileManager;
    }

    /**
     * Function to obtain a ServiceConnector knowing the profile of interest.
     *
     * @param profileId the identifier of the profile of interest.
     * @return a ServiceConnector for te Service implementing the profile.
     * @throws APosException if it is not possible to create the ServiceConnector.l
     */
    public ServiceConnector getServiceConnectorForProfileId(String profileId) throws APosException {
        Creation creation = _profileManager.getConfigurationForProfileId(profileId).getAsProfileConfiguration().getCreation();
        return new ServiceConnector(profileId, creation.getFactoryClass(), creation.getServiceClass());
    }

    /**
     * Function to get the IHS helper. Useful to parse IHS information.
     *
     * @return the IhsHelper.
     */
    public IhsHelper getIhsHelper() {
        return _ihsHelper;
    }

    /**
     * Function to get the ECI Encodig helper. Useful to parse ECI tag.
     *
     * @return the EciHelper.
     */
    public EciHelper getEciHelper() {
        return _eciHelper;
    }

    /**
     * Function to get the Label helper. Useful to parse barcode's label ids.
     *
     * @return the LabelHelper.
     */
    public LabelHelper getLabelHelper() {
        return _labelHelper;
    }
}
