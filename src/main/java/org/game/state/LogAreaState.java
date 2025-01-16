package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class LogAreaState extends GameComponentState {
    private String label;
    private ArrayList<String> log;
}
