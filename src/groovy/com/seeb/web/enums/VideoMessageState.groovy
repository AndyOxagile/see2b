package com.seeb.web.enums

/**
 * Created by savitskyao on 18.05.2015.
 */
public enum VideoMessageState {
    MISSED("Missed"),
    TALKED("Talked"),
    LEFT("Left"),

    private VideoMessageState(String id) {
        this.id = id
    }

    final String id
}
