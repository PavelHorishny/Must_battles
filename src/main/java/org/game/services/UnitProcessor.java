package org.game.services;

import lombok.Getter;
import org.game.State;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.HashMap;
import java.util.Map;


@Getter
public class UnitProcessor implements UnitService{

    private final MapService mapProcessor = new MapProcessor();
    private final FortificationService fortificationProcessor = new FortificationProcessor();
    private Surface[][] map;
    private Map <String, GameUnit> fleet = new HashMap<>();

    //NamesRandomizer namesRandomizer = new NamesRandomizer();
    public UnitProcessor() {
        //giveFortNames();
    }
    void giveFortNames(){
/*        MockedData.STANDARD_FORT_POSITION.forEach(e->{
            if(e.getFortificationType()==FortificationType.ROYAL_PORT){
                e.setId(namesRandomizer.royalPortNames.pop());
                fleet.put(e.getId(), e);
            }else {
                e.setId(namesRandomizer.fortificationsNames.pop());
                fleet.put(e.getId(),e);
            }
        });*/
    }

    @Override
    public State initialGameState() {
        map = mapProcessor.generateStandardMap();
        fortificationProcessor.generateFortification(fleet);
        return State.builder().map(map).fleet(fleet).build();
    }
}
