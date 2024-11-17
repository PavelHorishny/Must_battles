package org.game.services;

import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.List;
import java.util.Map;

public class FortificationProcessor implements FortificationService{
    private final NamesRandomizer namesRandomizer;
    public FortificationProcessor() {
        namesRandomizer = new NamesRandomizer();
    }
    @Override
    public void setNames(Map<String, GameUnit> fleet, List<Fortification> fortifications){
        fortifications.forEach(e->{
            if(e.getFortificationType()== FortificationType.ROYAL_PORT){
                e.setId(namesRandomizer.royalPortNames.pop());
                fleet.put(e.getId(), e);
            }else {
                e.setId(namesRandomizer.fortificationsNames.pop());
                fleet.put(e.getId(),e);
            }
        });
    }
}
