package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogAreaState extends GameComponentState {
    private String label;
}
