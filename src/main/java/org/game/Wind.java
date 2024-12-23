package org.game;

import lombok.Getter;

@Getter
public enum Wind {
    BREEZE("BREEZE"),CALM("CALM"),STORM("STORM");
    private final String value;
    Wind(String value){
        this.value=value;
    }

    public static final Wind[] windForce = new Wind[]{
            BREEZE,CALM,STORM
    };
}
