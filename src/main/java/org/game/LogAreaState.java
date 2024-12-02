package org.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogAreaState extends GameComponentState{
    String label;
    public LogAreaState() {

    }

    public LogAreaState(String label) {
        this.label = label;
    }

}
