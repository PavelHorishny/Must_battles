package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class State {
    private MapAreaState mapAreaState;
    private LogAreaState logAreaState;
    private InfoAreaState infoAreaState;
    private WindRoseAreaState windRoseAreaState;
    private ButtonAreaState buttonAreaState;
}
