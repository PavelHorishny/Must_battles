package org.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.map.Surface;
import org.game.unit.GameUnit;

import java.util.Map;

@Getter
@Setter
@Builder
public class State {
    Surface [] [] map;
    Map <String, GameUnit> fleet;
    MapAreaState mapAreaState;
    LogAreaState logAreaState;
    public State update(){
        mapAreaState = new MapAreaState();
        mapAreaState.setMap(BackToGUIConverter.convertMap(map));
        //mapAreaState.setFleet();
        return this;
    }
}
