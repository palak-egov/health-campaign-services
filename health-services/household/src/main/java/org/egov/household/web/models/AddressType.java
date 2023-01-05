package org.egov.household.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AddressType {
    PERMANENT("PERMANENT"),

    CORRESPONDENCE("CORRESPONDENCE"),

    OTHER("OTHER");

    private String value;

    AddressType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static AddressType fromValue(String text) {
        for (AddressType b : AddressType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return AddressType.OTHER;
    }
}
