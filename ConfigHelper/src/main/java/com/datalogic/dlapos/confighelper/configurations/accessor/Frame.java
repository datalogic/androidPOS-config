package com.datalogic.dlapos.confighelper.configurations.accessor;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id", "type"})
class Frame {

    @NonNull
    final private String id;
    final private String fieldName;
    final private String additionalData;
    @NonNull
    final private String type;

    Frame(@NonNull String id, String fieldName, String additionalData, @NonNull String type) {
        this.id = id;
        this.fieldName = fieldName;
        this.additionalData = additionalData;
        this.type = type;
    }

    @NonNull
    String getId() {
        return id;
    }

    String getFieldName() {
        return fieldName;
    }

    String getAdditionalData() {
        return additionalData;
    }

    @NonNull
    String getType() {
        return type;
    }
}
