package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class State {
    MapAreaState mapAreaState;
    LogAreaState logAreaState;
    InfoAreaState infoAreaState;
    WindRoseAreaState windRoseAreaState;
    ButtonAreaState buttonAreaState;
}
