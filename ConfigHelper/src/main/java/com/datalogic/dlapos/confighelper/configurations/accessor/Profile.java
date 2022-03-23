package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Profile {
    @PrimaryKey
    @NonNull
    final String profileId;

    final String profile;

    public Profile(@NonNull String profileId, String profile) {
        this.profile = profile;
        this.profileId = profileId;
    }

}
