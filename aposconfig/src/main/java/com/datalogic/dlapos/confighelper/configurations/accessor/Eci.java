package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Eci {

    @PrimaryKey
    final int eciCode;
    final String javaCode;

    Eci(int eciCode, String javaCode) {
        this.eciCode = eciCode;
        this.javaCode = javaCode;
    }

}
