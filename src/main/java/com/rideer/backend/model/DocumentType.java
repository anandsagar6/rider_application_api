package com.rideer.backend.model;

public enum DocumentType {
    DL_FRONT,
    DL_BACK,
    PROFILE_PHOTO,
    RC,
    INSURANCE;

    public String toFileName() {
        return name().toLowerCase();
    }
}
