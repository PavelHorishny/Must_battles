package org.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogAreaState extends GameComponentState{
    String label;
    public LogAreaState() {

    }

    public LogAreaState(String label) {
        this.label = label;
    }

}
