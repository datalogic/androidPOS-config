package com.datalogic.dlapos.confighelper.configurations.support;

import java.util.HashMap;

interface MappableConfiguration {
    /**
     * Function to convert the profile into an HashMap, queryable knowing properties id.
     *
     * @return the HashMap representation of the profile.
     */
    HashMap<String, String> toHashMap();
}
