package org.game.state;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class State {
    private MapAreaState mapAreaState;
    private LogAreaState logAreaState;
    private InfoAreaState infoAreaState;
    private WindRoseAreaState windRoseAreaState;
    private ButtonAreaState buttonAreaState;
}
