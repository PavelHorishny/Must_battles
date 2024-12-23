package org.game.services;

import org.game.Context;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;
import org.game.unit.GameUnit;

import java.util.*;

public class FortificationProcessor implements FortificationService{

    private final NamesRandomizer namesRandomizer;

    public FortificationProcessor() {
        namesRandomizer = Context.getNameRandomizer();
    }

/*    @Override
    public void generateFortification(Map<String, GameUnit> fleet) {
        setFortificationNames(fleet, MockedData.STANDARD_FORT_POSITION);
        //TODO check if this method redundant
    }*/
    /**
     * Accepts Surface multidimensional array and Map <String, GameUnit>
     * void
     * Set random names for Fortifications, update Map*/
    @Override
    public void getStandardFortifications(Surface[][] map, Map<String, GameUnit> fleet) {
        ArrayList <Fortification> tmp = MockedData.STANDARD_FORT_POSITION;
        setFortificationNames(fleet,tmp);
    }

    /**
     * Method accepts Map and List of Fortification Objects
     * sets a random name to each object
     * fulfill Map with Object's id as a key and Object itself as a value*/
    private void setFortificationNames(Map<String, GameUnit> fleet, List<Fortification> fortifications){
        fortifications.forEach(e->{
            if(e.getFortificationType()== FortificationType.ROYAL_PORT){
                e.setId(namesRandomizer.royalPortNames.pop());
            }else {
                e.setId(namesRandomizer.fortificationsNames.pop());
            }
            fleet.put(e.getId(), e);
        });
    }
    /**
     * Method accepts Surface array and Fortification object
     * switches SurfaceType to PORT
     * adds Surface to port Object's field */
    @Override
    public void setPortLocations(Set<Surface> port, Fortification fortification) {
        port.forEach(surface -> fortification.getPort().add(surface));
    }
}
