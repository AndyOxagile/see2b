package com.seeb.web.enums

/**
 * Created by savitskyao on 18.05.2015.
 */
public enum SessionState {
    INACTIVE("Inactive"),
    CLIENT_CALL("ClientCall"),
    COMPANY_CALL("CompanyCall"),
    IN_CALL("InCall"),
    RECORD_MESSAGE("RecordMessage")


    private SessionState(String id) {
        this.id = id
    }

    final String id
}
